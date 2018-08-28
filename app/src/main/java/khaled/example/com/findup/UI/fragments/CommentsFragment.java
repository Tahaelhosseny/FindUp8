package khaled.example.com.findup.UI.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.CommentsAdapter;
import khaled.example.com.findup.models.Comment;

public class CommentsFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("Nof Ahmed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/26167130_1598420403578018_2434073418497810718_n.jpg?_nc_cat=0&oh=fe9593c83468b97c82a5e1623cc99030&oe=5BC620E6"));
        commentList.add(new Comment("Ali Mohamed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/23032777_1542237902529602_2168190355513235328_n.jpg?_nc_cat=0&oh=bbc2dce33830def8b69357824a77d8f7&oe=5BE23CBC"));
        commentList.add(new Comment("Mohamed Ahmed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/20108103_1445001665586560_8405913558571444834_n.jpg?_nc_cat=0&oh=d1e92942903ee55336709c7e670b95af&oe=5BC99CF4"));
        commentList.add(new Comment("Walid Abd EL-Rahman",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/15337648_1214159182004144_1442355796478199942_n.jpg?_nc_cat=0&oh=9d510e190e24f0d97efcb03db4875f9b&oe=5BDF92C5"));

        RecyclerView recyclerView = getActivity().findViewById(R.id.comments_recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CommentsAdapter adapter = new CommentsAdapter(getActivity(), commentList);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);

        Button write_comment = getActivity().findViewById(R.id.write_comment_btn);
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
        View view =inflater.inflate(R.layout.comment_layout, container, false);
        return view;
    }
}
