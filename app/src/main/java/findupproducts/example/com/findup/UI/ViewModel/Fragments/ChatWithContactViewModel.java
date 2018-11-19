package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetChatContactResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetFullChatResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SendChatResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.adapters.ChatStoreContactPicAdapter;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.UI.adapters.MessageListAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.models.GetChat;
import findupproducts.example.com.findup.models.GetContact;
import findupproducts.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatWithContactViewModel extends Observable {
    private Context mContext;
    private int userId = -1;
    List<GetChat> messageList;
    private MessageListAdapter mMessageAdapter;

    public ChatWithContactViewModel(Context mContext){
        this.mContext = mContext;
        messageList = new ArrayList<>();
    }

    public void GetContact(RecyclerView contactsRecyclerView, RecyclerView mMessageRecycler){
        Log.e("MyData", ""+SharedPrefManger.getStore_ID());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetChatContactResponse> getStoreContacts = apiService.getContacts(SharedPrefManger.getStore_ID() , "Store");
        getStoreContacts.enqueue(new Callback<GetChatContactResponse>() {
            @Override
            public void onResponse(Call<GetChatContactResponse> call, Response<GetChatContactResponse> response) {
                Log.e("MyData", new Gson().toJson(response.body()));
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                    List<GetContact> contacts = response.body().getGetStoreContacts();
                    InitRecycler(contacts , contactsRecyclerView, mMessageRecycler);
                }else{
                    Toast.makeText(mContext, "There is Problem Occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetChatContactResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendMessageToUser(EditText messageEdit){
        if (userId == -1)
            Toast.makeText(mContext, "Please select contact", Toast.LENGTH_SHORT).show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SendChatResponse> sendMessage = apiService.sendMessage(SharedPrefManger.getStore_ID() , userId , "Store" , "User" , "" ,messageEdit.getText().toString() );
        sendMessage.enqueue(new Callback<SendChatResponse>() {
            @Override
            public void onResponse(Call<SendChatResponse> call, Response<SendChatResponse> response) {
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
                messageList.clear();
                mMessageAdapter.notifyDataSetChanged();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFullChatInStoreUI(RecyclerView mMessageRecycler, int userId){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetFullChatResponse> getFullChat = apiService.getChatHistory(userId , SharedPrefManger.getStore_ID() , "Store");
        getFullChat.enqueue(new Callback<GetFullChatResponse>() {
            @Override
            public void onResponse(Call<GetFullChatResponse> call, Response<GetFullChatResponse> response) {
                if(response.body().getSuccess() == 1){
                    Log.e("MyData", new Gson().toJson(response.body()));
                    Log.e("MyData", new Gson().toJson(response.body()));
                    Log.e("MyData", ""+userId);
                    Log.e("MyData", ""+SharedPrefManger.getUser_ID());
                    messageList = response.body().getGetChatMessage();
                    mMessageAdapter = new MessageListAdapter(mContext, messageList,"store");
                    mMessageRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                    mMessageRecycler.setAdapter(mMessageAdapter);
                    mMessageRecycler.scrollToPosition(response.body().getGetChatMessage().size()-1);
                    mMessageAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(mContext, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFullChatResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void InitRecycler(List<GetContact> contacts , RecyclerView contactsRecyclerView,RecyclerView mMessageRecycler){
        contactsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final ChatStoreContactPicAdapter adapter = new ChatStoreContactPicAdapter(mContext, contacts , 2);
        contactsRecyclerView.setAdapter(adapter);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(contactsRecyclerView);
        contactsRecyclerView.smoothScrollToPosition(2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        contactsRecyclerView.setLayoutManager(layoutManager);

        MiddleItemFinder.MiddleItemCallback callback = new MiddleItemFinder.MiddleItemCallback() {
            @Override
            public void scrollFinished(int middleElement) {
                userId = contacts.get(middleElement).getId();
                adapter.setMiddle_element_position(middleElement);
                adapter.notifyDataSetChanged();
                getFullChatInStoreUI(mMessageRecycler,userId);
            }
        };

        contactsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, mMessageRecycler, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                userId = contacts.get(position).getId();
                getFullChatInStoreUI(mMessageRecycler,userId);
                adapter.setMiddle_element_position(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        contactsRecyclerView.addOnScrollListener(new MiddleItemFinder(mContext, layoutManager,callback, RecyclerView.SCROLL_STATE_IDLE));
    }
}
