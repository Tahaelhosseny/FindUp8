package khaled.example.com.findup.UI.CustomViews;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class OverlapDecoration extends RecyclerView.ItemDecoration {

    private static int vertOverlap = -90;

    public static void resetVertOverlap() {
        OverlapDecoration.vertOverlap = -90;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == 0) {
            resetVertOverlap();
            return;
        }
        outRect.set(vertOverlap, 0, 0, 0);
        vertOverlap += 10;

    }
}