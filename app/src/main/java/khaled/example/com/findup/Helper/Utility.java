package khaled.example.com.findup.Helper;

import android.content.Context;
import android.content.res.TypedArray;

import khaled.example.com.findup.R;

public class Utility {

    public static int[] getCategoryBackgroundIDSArray(Context mContext){
        TypedArray ta = mContext.getResources().obtainTypedArray(R.array.colors);
        int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        return colors;
    }

}
