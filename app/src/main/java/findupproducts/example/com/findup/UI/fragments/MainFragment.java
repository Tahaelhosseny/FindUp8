package findupproducts.example.com.findup.UI.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.Store;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;

public class MainFragment extends Fragment {
    public static String eventType = "MainEvents";

    public MainFragment() {
        // Required empty public constructor
    }

    NearMeFragment nearMeFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    EventsFragment eventsFragment;
    List<Event> events = new ArrayList<>();
    private String TAG = "MainFragment";
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FrameLayout frameLayout = getActivity().findViewById(R.id.navigation_bottom_container);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        ImageButton search_filter = getActivity().findViewById(R.id.search_filter);
        search_filter.setVisibility(View.GONE);

        LinearLayout search_layout = getActivity().findViewById(R.id.search_edit_text_with_filter);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
            search_layout.setElevation(0);
        EditText search = getActivity().findViewById(R.id.search);

        filterData.setSearch_text(search.getText().toString());
        nearMeFragment = new NearMeFragment();
        eventsFragment = new EventsFragment();


        getChildFragmentManager().beginTransaction().replace(R.id.catsContainer, new MainCatsFragment()).commit();
        InitNearMeAndEventsFragments();


        disposable.add(RxTextView.textChangeEvents(search)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                /*.filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        return TextUtils.isEmpty(textViewTextChangeEvent.text().toString()) || textViewTextChangeEvent.text().toString().length() > 2;
                    }
                })*/
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchContacts()));

    }

    List<Store> stores = new ArrayList<>();

    private void InitNearMeAndEventsFragments() {
        getChildFragmentManager().beginTransaction().replace(R.id.nearMeContainer, nearMeFragment).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.eventsContainer, eventsFragment).commit();
    }

    private DisposableObserver<TextViewTextChangeEvent> searchContacts() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("search_text", textViewTextChangeEvent.getText().toString());
                //nearMeFragment = new NearMeFragment();
                nearMeFragment.setArguments(bundle);
                eventsFragment.setArguments(bundle);
                //InitNearMeAndEventsFragments();

                //mAdapter.getFilter().filter(textViewTextChangeEvent.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
