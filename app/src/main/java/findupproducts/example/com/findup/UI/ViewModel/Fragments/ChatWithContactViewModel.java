package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetChatContactResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.adapters.ChatStoreContactPicAdapter;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.models.GetContact;
import findupproducts.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatWithContactViewModel extends Observable {
    private Context mContext;
    public ChatWithContactViewModel(Context mContext){this.mContext = mContext;}
    public void GetContact(RecyclerView recyclerView){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetChatContactResponse> getStoreContacts = apiService.getContacts(SharedPrefManger.getStore_ID() , "Store");
        getStoreContacts.enqueue(new Callback<GetChatContactResponse>() {
            @Override
            public void onResponse(Call<GetChatContactResponse> call, Response<GetChatContactResponse> response) {
                if(response.body().getSuccess() == 1){
                    List<GetContact> contacts = new ArrayList<>();
                    contacts = response.body().getGetStoreContacts();
                    InitRecycler(contacts , recyclerView);
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
    private void InitRecycler(List<GetContact> contacts , RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final ChatStoreContactPicAdapter adapter = new ChatStoreContactPicAdapter(mContext, contacts , 2);
        recyclerView.setAdapter(adapter);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.smoothScrollToPosition(2);
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
