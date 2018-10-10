package khaled.example.com.findup.UI.CustomViews;

import android.support.v4.view.ViewPager;
import android.view.View;

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.8f;

    public void transformPage(View view, float position) {
        float translationX;
        float scale;
        float alpha;

        if (position >= 1 || position <= -1) {
            // Fix for https://code.google.com/p/android/issues/detail?id=58918
            translationX = 0;
            scale = 1;
            alpha = 1;
        } else if (position >= 0) {
            translationX = -view.getWidth() * position;
            scale = -0.2f * position + 1;
            alpha = Math.max(1 - position, 0);
        } else {
            translationX = 0.5f * view.getWidth() * position;
            scale = 1.0f;
            alpha = Math.max(0.1f * position + 1, 0);
        }

        view.setTranslationX(translationX);
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setAlpha(alpha);
    }
}