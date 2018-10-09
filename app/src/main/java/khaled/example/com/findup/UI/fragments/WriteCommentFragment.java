package khaled.example.com.findup.UI.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Fragments.AddCommentStorePresenter;
import khaled.example.com.findup.UI.ViewModel.Fragments.AddCommentStoreViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.ProductCommentsViewModel;
import khaled.example.com.findup.databinding.WriteCommentLayoutBinding;

public class WriteCommentFragment extends Fragment {
    WriteCommentLayoutBinding writeCommentLayoutBinding;
    AddCommentStoreViewModel addCommentStoreViewModel;
    Button write_new_comment_btn;
    EditText commentText ;
    public WriteCommentFragment(){}
    public static WriteCommentFragment newInstance() {
        WriteCommentFragment fragment = new WriteCommentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        writeCommentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.write_comment_layout, container, false);

        View view = writeCommentLayoutBinding.getRoot();
        write_new_comment_btn = view.findViewById(R.id.write_comment_btn);
        commentText = view.findViewById(R.id.editText);
        Intent i = getActivity().getIntent();
        int store_id = i.getIntExtra("store_id",1);
        String account_id = SharedPrefManger.getUser_ID();
        Toast.makeText(getActivity(), ""+store_id, Toast.LENGTH_SHORT).show();
        addCommentStoreViewModel = new AddCommentStoreViewModel(view.getContext());
        writeCommentLayoutBinding.setPresenter(new AddCommentStorePresenter() {
            @Override
            public void addCommentToStore() {
                String comment = commentText.getText().toString();
                if(comment.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter Comment Field", Toast.LENGTH_SHORT).show();
                }else{
                    addCommentStoreViewModel.addCommentToStore(SharedPrefManger.getUser_ID() , commentText.getText().toString() , store_id);
                }
            }
        });
        //here data must be an instance of the class MarsDataProvider

        return view;
    }
}
