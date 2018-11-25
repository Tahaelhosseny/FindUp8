package findupproducts.example.com.findup.UI.ViewModel.Activites;

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

import java.net.ConnectException;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetFullChatResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SendChatResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.activities.SpecificChatWithStore;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.UI.adapters.MessageListAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.models.GetChat;
import findupproducts.example.com.findup.models.SendMessage;
import findupproducts.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecificChatViewModel extends Observable {
    private Context mContext;
    private List<GetChat> messageList;
    private MessageListAdapter mMessageAdapter;
    public SpecificChatViewModel(Context mContext){
        this.mContext = mContext;
    }
    public void sendMessageToStore(int storeId , EditText messageEdit){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SendChatResponse> sendMessage = apiService.sendMessage(SharedPrefManger.getUser_ID() , storeId , "User" , "Store" , "" ,messageEdit.getText().toString() );
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

    public void getFullChat(RecyclerView mMessageRecycler, int storeId){
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
                    Toast.makeText(mContext, "There is no message with this store yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFullChatResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
