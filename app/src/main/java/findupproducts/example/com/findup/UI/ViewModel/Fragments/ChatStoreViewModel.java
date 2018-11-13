package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetFullChatResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SearchStoreResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SendChatResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.UI.adapters.MessageListAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.models.GetChat;
import findupproducts.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatStoreViewModel extends Observable {
    private Context mContext;
    private int storeId = -1;
    private List<GetChat> messageList;
    private MessageListAdapter mMessageAdapter;

    public ChatStoreViewModel(Context mContext){this.mContext = mContext;}

    public void GetStoresForChat(RecyclerView storesRecyclerView, RecyclerView mMessageRecycler){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoresResponse> getStoresForChat = apiService.GetAllStores(String.valueOf(SharedPrefManger.getUser_ID()));
        getStoresForChat.enqueue(new Callback<StoresResponse>() {
            @Override
            public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                if(response.body().getSuccess() == 1){
                    List<Store> stores = response.body().getData();
                    InitRecycler(stores , storesRecyclerView, mMessageRecycler);
                }else{
                    Toast.makeText(mContext, "There is Problem Occurred", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StoresResponse> call, Throwable t) {
                //Toast.makeText(mContext, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }

    public void sendMessageToStore(EditText messageEdit){
        if (storeId== -1)
            Toast.makeText(mContext, "Please select contact", Toast.LENGTH_LONG).show();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SendChatResponse> sendMessage = apiService.sendMessage(SharedPrefManger.getUser_ID() , storeId, "User" , "Store" , "" ,messageEdit.getText().toString() );
        sendMessage.enqueue(new Callback<SendChatResponse>() {
            @Override
            public void onResponse(Call<SendChatResponse> call, Response<SendChatResponse> response) {
                //Log.e("MyData", new Gson().toJson(response.body()));
                if(response.body().getSuccess() == 1){
                    Log.e("MyData", "msg sent");
                    GetChat newMsg = new GetChat();
                    newMsg.setMsg_body(response.body().getGetChatMessage().get(0).getMsg_body());
                    newMsg.setSender_id(response.body().getGetChatMessage().get(0).getSender_id());
                    newMsg.setSender_type(response.body().getGetChatMessage().get(0).getSender_type());
                    messageList.add(newMsg);
                    mMessageAdapter.notifyDataSetChanged();
                    messageEdit.setText("");
                }
            }

            @Override
            public void onFailure(Call<SendChatResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFullChat(RecyclerView mMessageRecycler, int storeId){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetFullChatResponse> getFullChat = apiService.getChatHistory(SharedPrefManger.getUser_ID() , storeId , "User");
        getFullChat.enqueue(new Callback<GetFullChatResponse>() {
            @Override
            public void onResponse(Call<GetFullChatResponse> call, Response<GetFullChatResponse> response) {
                if(response.body().getSuccess() == 1){
                    Log.e("MyData", new Gson().toJson(response.body()));
                    Log.e("MyData", ""+storeId);
                    Log.e("MyData", ""+SharedPrefManger.getUser_ID());
                    messageList = response.body().getGetChatMessage();
                    mMessageAdapter = new MessageListAdapter(mContext, messageList,"user");
                    mMessageRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                    mMessageRecycler.setAdapter(mMessageAdapter);
                    mMessageRecycler.scrollToPosition(response.body().getGetChatMessage().size()-1);
                    mMessageAdapter.notifyDataSetChanged();
                }else {
                    messageList.clear();
                    mMessageAdapter.notifyDataSetChanged();
                    Toast.makeText(mContext, "No Chat with this store", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFullChatResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitRecycler(List<Store> stores , RecyclerView storesRecyclerView,RecyclerView mMessageRecycler){
        storesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final ChatStoresProfilePicAdapter adapter = new ChatStoresProfilePicAdapter(mContext, stores , stores.size()/2);
        storesRecyclerView.setAdapter(adapter);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(storesRecyclerView);
        //recyclerView.smoothScrollToPosition(2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        storesRecyclerView.setLayoutManager(layoutManager);

        MiddleItemFinder.MiddleItemCallback callback = new MiddleItemFinder.MiddleItemCallback() {
            @Override
            public void scrollFinished(int middleElement) {
                storeId = stores.get(middleElement).getStore_id();
                getFullChat(mMessageRecycler,storeId);
                adapter.setMiddle_element_position(middleElement);
                adapter.notifyDataSetChanged();
            }
        };

        //storesRecyclerView.addOnScrollListener(new MiddleItemFinder(mContext, layoutManager,callback, RecyclerView.SCROLL_STATE_IDLE));
        storesRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, mMessageRecycler, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                storeId = stores.get(position).getStore_id();
                getFullChat(mMessageRecycler,storeId);
                adapter.setMiddle_element_position(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}

