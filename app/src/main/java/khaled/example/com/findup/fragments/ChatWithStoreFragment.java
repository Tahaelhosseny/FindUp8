package khaled.example.com.findup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.adapters.MessageListAdapter;
import khaled.example.com.findup.models.UserMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatWithStoreFragment extends Fragment {


    public ChatWithStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_with_gen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView mMessageRecycler = getActivity().findViewById(R.id.reyclerview_message_list);
        List<UserMessage> messageList = new ArrayList<>();
        messageList.add(new UserMessage(1, "Hello!"));
        messageList.add(new UserMessage(2, "Hello!"));
        messageList.add(new UserMessage(1, "Hello!"));
        messageList.add(new UserMessage(1, "Hello!"));
        messageList.add(new UserMessage(2, "Hello!"));
        messageList.add(new UserMessage(1, "Hello!"));
        MessageListAdapter mMessageAdapter = new MessageListAdapter(getActivity(), messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }
}
