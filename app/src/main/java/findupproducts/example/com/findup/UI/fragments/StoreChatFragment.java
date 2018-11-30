package findupproducts.example.com.findup.UI.fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ChatStoreViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ChatWithContactViewModel;
import findupproducts.example.com.findup.UI.adapters.MessageListAdapter;
import findupproducts.example.com.findup.UI.adapters.SendChatProductsAdapter;
import findupproducts.example.com.findup.databinding.FragmentChatWithGenBinding;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.UserMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreChatFragment extends Fragment {

    FragmentChatWithGenBinding binding;
    ChatWithContactViewModel viewModel;

    public StoreChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_with_gen, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        viewModel = new ChatWithContactViewModel(view.getContext());
        binding.setStoreContact(viewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.GetContact(binding.recyclerContactList,binding.reyclerviewMessageList);

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.sendMessageToUser(binding.reyclerviewMessageList , binding.chatboxEdit);
            }
        });


    }
}
