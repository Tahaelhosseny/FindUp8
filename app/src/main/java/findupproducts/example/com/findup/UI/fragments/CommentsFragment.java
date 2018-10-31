package findupproducts.example.com.findup.UI.fragments;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ProductCommentsViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ProductsViewModel;
import findupproducts.example.com.findup.databinding.CommentLayoutBinding;


public class CommentsFragment extends Fragment {
    ProductCommentsViewModel productCommentsViewModel;
    CommentLayoutBinding commentLayoutBinding;
    public static CommentsFragment newInstance() {
        CommentsFragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = getActivity().findViewById(R.id.comments_recyclerview);
        Button write_comment = getActivity().findViewById(R.id.write_comment_btn);
        Intent i = getActivity().getIntent();
        int store_id = 0;
        int product_id = 0;
        if (i.hasExtra("store_acc")) {
            store_id = i.getIntExtra("store_id", 0);
            write_comment.setVisibility(View.GONE);
        }
        else if(i.hasExtra("product_id")){
            product_id = i.getIntExtra("product_id" , 0);
        }else if (i.hasExtra("store_id")){
            store_id = i.getIntExtra("store_id", 0);
        }
        else{
            Toast.makeText(getActivity(), "Something went error", Toast.LENGTH_SHORT).show();
        }
        if(product_id == 0){
            productCommentsViewModel.InitRecyclerView(commentLayoutBinding.commentsRecyclerview,store_id , 1);
        }else{
            productCommentsViewModel.InitRecyclerView(commentLayoutBinding.commentsRecyclerview,product_id , 2);
        }
        write_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.comments_framelayout, new WriteCommentFragment()).commit();
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view =inflater.inflate(R.layout.comment_layout, container, false);
        commentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.comment_layout, container, false);
        View view = commentLayoutBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        productCommentsViewModel = new ProductCommentsViewModel(view.getContext());
        commentLayoutBinding.setProductComments(productCommentsViewModel);
        return view;
    }
}
