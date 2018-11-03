package findupproducts.example.com.findup.UI.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetFullChatResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ChatStoreViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ProductCommentsViewModel;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.UI.adapters.MessageListAdapter;
import findupproducts.example.com.findup.databinding.FragmentChatStoreBinding;
import findupproducts.example.com.findup.models.GetChat;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.UserMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatStoreFragment extends Fragment {

    ChatStoreViewModel viewModel;
    FragmentChatStoreBinding binding;

    public ChatStoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_store, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        viewModel = new ChatStoreViewModel(view.getContext());
        binding.setChatStore(viewModel);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.GetStoresForChat(binding.storesChatList);
        viewModel.getFullChat(binding.reyclerviewMessageList);



        Button addBtn = getActivity().findViewById(R.id.addBtn);

    }


}
