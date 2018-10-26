package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;

import java.util.Observable;

public class SortFilterViewModel extends Observable {
    private Context mContect;
    public SortFilterViewModel(Context mContext){
        this.mContect = mContext;
    }
}
