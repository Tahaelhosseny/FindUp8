package khaled.example.com.findup.Helper;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import khaled.example.com.findup.R;

public class Utility {

    public static String getDate(long time) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(new Date(time * 1000));
        Date date = new Date();
        try {
            date = sdf.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return localTime;
    }


    public static String getDateWithHours(long time) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm a");
        sdf.setTimeZone(tz);//set time zone.
        String localTime = sdf.format(new Date(time * 1000));
        Date date = new Date();
        try {
            date = sdf.parse(localTime);//get local date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return localTime;
    }

    public static void replaceFragment (FragmentManager manager, Fragment fragment , int containerID, int transition){
        String fragmentTag =  fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate (fragmentTag, 0);

        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){
            ft.replace(containerID, fragment, fragmentTag);
            if (transition == 0)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            else
                ft.setTransition(transition);
            ft.addToBackStack(fragmentTag);
            ft.commit();
        } else {
            ft.replace(containerID, manager.findFragmentByTag(fragmentTag));
            if (transition == 0)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            else
                ft.setTransition(transition);
            ft.commit();
        }
    }
}
