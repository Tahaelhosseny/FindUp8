package khaled.example.com.findup.UI.fragments;


import android.app.Dialog;
import android.content.DialogInterface;
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

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.SendChatProductsAdapter;
import khaled.example.com.findup.UI.adapters.MessageListAdapter;
import khaled.example.com.findup.models.Product;
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
        messageList.add(new UserMessage(1, "Hi ahmed!"));
        messageList.add(new UserMessage(2, "hi man!"));
        messageList.add(new UserMessage(1, "how are you"));
        messageList.add(new UserMessage(1, "Good man"));
        messageList.add(new UserMessage(2, "Thank you"));
        messageList.add(new UserMessage(1, "Good bye"));
        MessageListAdapter mMessageAdapter = new MessageListAdapter(getActivity(), messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessageRecycler.setAdapter(mMessageAdapter);



        final List<Product> products = new ArrayList<>();
        products.add(new Product(0,36,206,566,"Name", "Description","http://i.imgur.com/2KQVKw0.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","https://media-cdn.tripadvisor.com/media/photo-s/0e/40/f2/34/delicious-cofe.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","https://cdn2.stylecraze.com/wp-content/uploads/2013/09/1557_5-Black-Tea-Side-Effects-You-Should-Be-Aware-Of.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","http://i.imgur.com/2KQVKw0.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","https://i2-prod.mirror.co.uk/incoming/article6201545.ece/ALTERNATES/s615/Cup-of-tea.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","http://cdn.shopify.com/s/files/1/0653/8213/products/Review_1_1_595e822f-7ad5-42f2-8f04-d16c923614dd_grande.jpg?v=1520387592"));


        final Dialog dialog = initDialog(products);
        final EditText msg = dialog.findViewById(R.id.chatboxEdit);
        final EditText org_msg = getActivity().findViewById(R.id.chatboxEdit);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface d) {
                org_msg.setText(msg.getText().toString());
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                org_msg.setText(msg.getText().toString());
            }
        });

        Button addBtn = getActivity().findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText(org_msg.getText().toString());
                dialog.show();
                msg.setFocusable(true);
            }
        });
    }

    private Dialog initDialog(List<Product> products){
        Dialog dialog=new Dialog(getActivity(),R.style.fullScreeDialog);
        dialog.setContentView(R.layout.send_products_chat_layout);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        //params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        RecyclerView recyclerView = dialog.findViewById(R.id.reyclerview_send_products);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SendChatProductsAdapter adapter = new SendChatProductsAdapter(getActivity(), products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );
        return dialog;
    }
}
