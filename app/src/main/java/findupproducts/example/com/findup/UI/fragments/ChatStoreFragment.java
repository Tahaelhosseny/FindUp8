package findupproducts.example.com.findup.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.CustomViews.MiddleItemFinder;
import findupproducts.example.com.findup.UI.adapters.ChatStoresProfilePicAdapter;
import findupproducts.example.com.findup.UI.adapters.MessageListAdapter;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.UserMessage;

public class ChatStoreFragment extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_store, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "Black Cafe", "https://www.butlerschocolates.com/upload/637/cms/525995/en/39710/gallery.jpg", "4.5", "American Cafe Break $$"));
        stores.add(new Store(2, "Genuine Coffee", "https://viejas.com/wp-content/uploads/2018/01/Cafe_Patio_detail-1.jpg", "4.2", "Indian Cafe Break $$"));
        stores.add(new Store(1, "Black Cafe", "http://www.royalhotelchilliwack.com/Content/images/Hotel-Cafe-o.jpg", "4.5", "American Cafe Break $$"));
        stores.add(new Store(2, "Genuine Coffee", "https://www.butlerschocolates.com/upload/637/cms/525995/en/39710/gallery.jpg", "4.2", "Indian Cafe Break $$"));
        stores.add(new Store(1, "Black Cafe", "http://www.royalhotelchilliwack.com/Content/images/Hotel-Cafe-o.jpg", "4.5", "American Cafe Break $$"));
        stores.add(new Store(2, "Genuine Coffee", "https://www.butlerschocolates.com/upload/637/cms/525995/en/39710/gallery.jpg", "4.2", "Indian Cafe Break $$"));
        stores.add(new Store(1, "Black Cafe", "http://www.royalhotelchilliwack.com/Content/images/Hotel-Cafe-o.jpg", "4.5", "American Cafe Break $$"));
        stores.add(new Store(2, "Genuine Coffee", "https://www.butlerschocolates.com/upload/637/cms/525995/en/39710/gallery.jpg", "4.2", "Indian Cafe Break $$"));
        stores.add(new Store(1, "Black Cafe", "http://www.royalhotelchilliwack.com/Content/images/Hotel-Cafe-o.jpg", "4.5", "American Cafe Break $$"));
        stores.add(new Store(2, "Genuine Coffee", "https://www.butlerschocolates.com/upload/637/cms/525995/en/39710/gallery.jpg", "4.2", "Indian Cafe Break $$"));


        final RecyclerView recyclerView = getActivity().findViewById(R.id.stores_chat_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        final ChatStoresProfilePicAdapter adapter = new ChatStoresProfilePicAdapter(getActivity(), stores, 2);
        recyclerView.setAdapter(adapter);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.smoothScrollToPosition(2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        MiddleItemFinder.MiddleItemCallback callback =
                new MiddleItemFinder.MiddleItemCallback() {
                    @Override
                    public void scrollFinished(int middleElement) {
                        adapter.setMiddle_element_position(middleElement);
                        adapter.notifyDataSetChanged();
                    }
                };

        recyclerView.addOnScrollListener(
                new MiddleItemFinder(getContext(), layoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE));


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


        Button addBtn = getActivity().findViewById(R.id.addBtn);

    }


}
