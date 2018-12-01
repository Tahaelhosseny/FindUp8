package findupproducts.example.com.findup.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import java.util.List;
import java.util.concurrent.TimeUnit;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.LoginActivity;
import findupproducts.example.com.findup.UI.activities.MainActivity;
import findupproducts.example.com.findup.UI.activities.SignupActivity;
import findupproducts.example.com.findup.UI.activities.SplashScreenActivity;
import findupproducts.example.com.findup.models.Day;
import findupproducts.example.com.findup.models.Store_WorkTime;

public class UI_Utility {
    public static int[] getCategoryBackgroundIDSArray(Context mContext) {
        TypedArray ta = mContext.getResources().obtainTypedArray(R.array.colors);
        int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        return colors;
    }

    public static void BottomNavigationMenu_icons_change(Menu menu, int Selected) {
        menu.findItem(R.id.home).setIcon(R.drawable.home_unselected_3x);
        menu.findItem(R.id.map).setIcon(R.drawable.map_3x);
        menu.findItem(R.id.search).setIcon(R.drawable.search_3x);
        menu.findItem(R.id.category).setIcon(R.drawable.category_3x);
        menu.findItem(R.id.profile).setIcon(R.drawable._2_3x);

        if (Selected == 0)
            menu.getItem(Selected).setIcon(R.drawable.home_sel_3x);
        if (Selected == 1)
            menu.getItem(Selected).setIcon(R.drawable.map_sel_3x);
        if (Selected == 2)
            menu.getItem(Selected).setIcon(R.drawable.search_sel_3x);
        if (Selected == 3)
            menu.getItem(Selected).setIcon(R.drawable.category_sel_3x);
        if (Selected == 4)
            menu.getItem(Selected).setIcon(R.drawable._2_3x);
    }

    public static void BottomNavigationStoreMenu_icons_change(Menu menu, int Selected) {
        menu.findItem(R.id.storeHome).setIcon(R.drawable.home_unselected_3x);
        menu.findItem(R.id.storeChat).setIcon(R.drawable.chat_3x);
        menu.findItem(R.id.storeProfile).setIcon(R.drawable._2_3x);

        if (Selected == 0)
            menu.getItem(Selected).setIcon(R.drawable.home_sel_3x);
        if (Selected == 1)
            menu.getItem(Selected).setIcon(R.drawable.chat_selected_3x);
        if (Selected == 2)
            menu.getItem(Selected).setIcon(R.drawable._2_3x);
    }

    public static void switchVisibility(View view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        else if (view.getVisibility() == View.VISIBLE)
            view.setVisibility(View.GONE);
    }

    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }


    public static AlertDialog ShowProgressDialog(Context mContext, boolean isVisible) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.loading_dialoge_layout, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        AlertDialog b = dialogBuilder.create();
        if (isVisible)
            b.show();
        else
            b.dismiss();
        return b;
    }

    public static AlertDialog noConnection(Context mContext, boolean isVisible) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(mContext.getResources().getString(R.string.no_internet));
        builder.setMessage(mContext.getResources().getString(R.string.no_internet_connection));

        builder.setPositiveButton(mContext.getResources().getString(R.string.no_connection_try_again), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                mContext.startActivity(new Intent(mContext, SplashScreenActivity.class));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(mContext.getResources().getString(R.string.no_internet_continue), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                mContext.startActivity(new Intent(mContext, MainActivity.class));
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        if(!((Activity) mContext).isFinishing())
            alert.show();
        return alert;
    }

    public static String WorkDaysToString(List<Day> dayList) {
        if (dayList.size() == 7) {
            return dayList.get(0) + " - " + dayList.get(dayList.size() - 1);
        } else {
            String days = "";
            for (int i = 0; i < dayList.size(); i++) {
                days = days + "" + dayList.get(i).getDay_name();
                if (i != dayList.size() - 1)
                    days = days + " - ";
            }
            return days;
        }
    }

    public static String WorkTimeToString(Store_WorkTime store_workTime) {
        return store_workTime.getWork_from_time() + " - " + store_workTime.getWork_to_time();
    }

    public static String CountValueToString(int value,Context mContext){
        if (value > 1000000){
            return  String.format("%.2f",(value/1000000)).concat(mContext.getResources().getString(R.string.count_value_milion));
        }else if (value > 1000){
            return  String.format("%.2f",(value/1000)).concat(mContext.getResources().getString(R.string.count_value_k));
        }else
            return  String.valueOf(value);

    }

    public static String fromMinutesToHHmm(int min) {
        long hours = TimeUnit.MINUTES.toHours(Long.valueOf(min));
        long remainMinutes = min - TimeUnit.HOURS.toMinutes(hours);
        return String.format("%02d:%02d", hours, remainMinutes);
    }

    public static AlertDialog signInDialogue(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setMessage("Sign in first to access profile page!");
        builder.setPositiveButton("Sign up",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        context.startActivity(new Intent(context, SignupActivity.class));
                        dialog.cancel();
                    }
                });

        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton("Sign in",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        context.startActivity(new Intent(context, LoginActivity.class));
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}

