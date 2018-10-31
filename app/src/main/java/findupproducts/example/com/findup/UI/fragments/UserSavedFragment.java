package findupproducts.example.com.findup.UI.fragments;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.YouSavedViewModel;
import findupproducts.example.com.findup.databinding.FragmentUserSavedBinding;

public class UserSavedFragment extends Fragment {

    FragmentUserSavedBinding fragmentUserSavedBinding;
    YouSavedViewModel userSavedViewModel;

    public UserSavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUserSavedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_saved, container, false);
        View view = fragmentUserSavedBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        userSavedViewModel = new YouSavedViewModel(view.getContext());
        fragmentUserSavedBinding.setUserSaved(userSavedViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userSavedViewModel.InitRecycler(fragmentUserSavedBinding.userSavedRecyclerView);


    }
}
