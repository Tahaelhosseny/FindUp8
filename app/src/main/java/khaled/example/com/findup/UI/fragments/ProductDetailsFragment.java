package khaled.example.com.findup.UI.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.CustomViews.OverlapDecoration;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.UI.activities.PhotosGalleryActivity;
import khaled.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.UI.adapters.StorePhotosAdapter;
import khaled.example.com.findup.models.Comment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<String> photos = new ArrayList<>();
        photos.add("");
        photos.add("");
        photos.add("");
        photos.add("");
        photos.add("");
        bindPhotos(photos);
        bindCommentsPhotos();
    }


    private void bindPhotos(List<String > photos){
        RecyclerView recyclerView = getActivity().findViewById(R.id.productPhotosRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StorePhotosAdapter adapter = new StorePhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), PhotosGalleryActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    private void bindCommentsPhotos(){
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("Nof Ahmed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/26167130_1598420403578018_2434073418497810718_n.jpg?_nc_cat=0&oh=fe9593c83468b97c82a5e1623cc99030&oe=5BC620E6"));
        commentList.add(new Comment("Ali Mohamed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/23032777_1542237902529602_2168190355513235328_n.jpg?_nc_cat=0&oh=bbc2dce33830def8b69357824a77d8f7&oe=5BE23CBC"));
        commentList.add(new Comment("Mohamed Ahmed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/20108103_1445001665586560_8405913558571444834_n.jpg?_nc_cat=0&oh=d1e92942903ee55336709c7e670b95af&oe=5BC99CF4"));
        commentList.add(new Comment("Walid Abd EL-Rahman",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/15337648_1214159182004144_1442355796478199942_n.jpg?_nc_cat=0&oh=9d510e190e24f0d97efcb03db4875f9b&oe=5BDF92C5"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        RecyclerView recyclerView = getActivity().findViewById(R.id.commentUsersImg);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommentsPhotosAdapter adapter = new CommentsPhotosAdapter(getActivity(), commentList);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), CommentsActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
