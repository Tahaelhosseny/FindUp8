package khaled.example.com.findup.UI.fragments;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import khaled.example.com.findup.UI.ViewModel.Fragments.ProductDetailViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.StoreDetailsViewModel;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.databinding.FragmentProductDetailsBinding;
import khaled.example.com.findup.models.Comment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    FragmentProductDetailsBinding binding;
    ProductDetailViewModel productDetailViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false);
        View view = binding.getRoot();
        Intent i = ((Activity) view.getContext()).getIntent();
        int store_id = 1;
        if (i.hasExtra("prod_id"))
            store_id = i.getIntExtra("prod_id", 1);
        productDetailViewModel = new ProductDetailViewModel(view.getContext(), store_id);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        productDetailViewModel.bindProductData(binding.productBanner,binding.productName,binding.productPrice,binding.productStoreTxt,binding.productLikeCount,binding.aboutProduct,
                binding.productPhotosRecycler,binding.commentUsersTxt,binding.commentUsersNumTxt,
            binding.showProductComment , binding.commentUsersImg);

    }
    private void bindCommentsPhotos() {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("Nof Ahmed", 1532037763, "There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.", "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/26167130_1598420403578018_2434073418497810718_n.jpg?_nc_cat=0&oh=fe9593c83468b97c82a5e1623cc99030&oe=5BC620E6"));
        commentList.add(new Comment("Ali Mohamed", 1532037763, "There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.", "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/23032777_1542237902529602_2168190355513235328_n.jpg?_nc_cat=0&oh=bbc2dce33830def8b69357824a77d8f7&oe=5BE23CBC"));
        commentList.add(new Comment("Mohamed Ahmed", 1532037763, "There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.", "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/20108103_1445001665586560_8405913558571444834_n.jpg?_nc_cat=0&oh=d1e92942903ee55336709c7e670b95af&oe=5BC99CF4"));
        commentList.add(new Comment("Walid Abd EL-Rahman", 1532037763, "There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.", "https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/15337648_1214159182004144_1442355796478199942_n.jpg?_nc_cat=0&oh=9d510e190e24f0d97efcb03db4875f9b&oe=5BDF92C5"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
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
