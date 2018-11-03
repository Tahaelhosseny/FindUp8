package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetFullChatResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SearchStoreResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.models.GetChat;
import findupproducts.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatStoreViewModel extends Observable {
    private Context mContext;
    public ChatStoreViewModel(Context mContext){this.mContext = mContext;}
    public void GetStoresForChat(RecyclerView recyclerView){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoresResponse> getStoresForChat = apiService.GetAllStores(SharedPrefManger.getUser_ID());
        getStoresForChat.enqueue(new Callback<StoresResponse>() {
            @Override
            public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                if(response.body().getSuccess() == 1){
                    List<Store> stores = response.body().getData();
                    InitRecycler(stores , recyclerView);
                }else{
                    Toast.makeText(mContext, "There is Problem Occurred", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StoresResponse> call, Throwable t) {
                Toast.makeText(mContext, "Failure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getFullChat(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetFullChatResponse> getFullChat = apiService.getChatHistory(SharedPrefManger.getUser_ID() , 1 , "User");
        getFullChat.enqueue(new Callback<GetFullChatResponse>() {
            @Override
            public void onResponse(Call<GetFullChatResponse> call, Response<GetFullChatResponse> response) {
                if(response.body().getSuccess() == 1){
                    List<GetChat> chatMessages = response.body().getGetChatMessage();
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

    private void InitRecycler(List<Store> stores , RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final ChatStoresProfilePicAdapter adapter = new ChatStoresProfilePicAdapter(mContext, stores , stores.size()/2);
        recyclerView.setAdapter(adapter);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        //recyclerView.smoothScrollToPosition(2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        MiddleItemFinder.MiddleItemCallback callback = new MiddleItemFinder.MiddleItemCallback() {
            @Override
            public void scrollFinished(int middleElement) {
                adapter.setMiddle_element_position(middleElement);
                adapter.notifyDataSetChanged();
            }
        };

        recyclerView.addOnScrollListener(new MiddleItemFinder(mContext, layoutManager,callback, RecyclerView.SCROLL_STATE_IDLE));
    }
}

