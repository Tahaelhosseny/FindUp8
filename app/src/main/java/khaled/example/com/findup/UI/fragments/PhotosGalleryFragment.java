package khaled.example.com.findup.UI.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.GalleryAdapter;
import khaled.example.com.findup.models.Store;

public class PhotosGalleryFragment extends Fragment {


    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();


    }

    private void initRecyclerView() {
        List<String> url = new ArrayList<>();
        url.add("https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/23032777_1542237902529602_2168190355513235328_n.jpg?_nc_cat=0&oh=bbc2dce33830def8b69357824a77d8f7&oe=5BE23CBC");
        url.add("https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/20108103_1445001665586560_8405913558571444834_n.jpg?_nc_cat=0&oh=d1e92942903ee55336709c7e670b95af&oe=5BC99CF4");
        url.add("https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/15337648_1214159182004144_1442355796478199942_n.jpg?_nc_cat=0&oh=9d510e190e24f0d97efcb03db4875f9b&oe=5BDF92C5");

        Store store = new Store(2, "Genuine Coffee", "", "4.2", "Indian Cafe Break $$");
        GalleryAdapter galleryAdapter = new GalleryAdapter(getContext(), url, store);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.photo_recyclerview);
        recyclerView.setAdapter(galleryAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new CardSliderLayoutManager(getContext()));
        recyclerView.setLayoutManager(new CardSliderLayoutManager(1, 900, 0));

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();
        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_gallery_layout, container, false);
        return view;
    }


}
