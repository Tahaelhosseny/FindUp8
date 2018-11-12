package findupproducts.example.com.findup.Helper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.ChatWithStoreActivity;
import findupproducts.example.com.findup.UI.activities.SpecificChatWithStore;
import findupproducts.example.com.findup.UI.fragments.CategoryFragment;
import findupproducts.example.com.findup.UI.fragments.ChatWithStoreFragment;
import findupproducts.example.com.findup.UI.fragments.MainFragment;
import findupproducts.example.com.findup.UI.fragments.MapFragment;
import findupproducts.example.com.findup.UI.fragments.ProfileFragment;
import findupproducts.example.com.findup.UI.fragments.ProfileStoreFragment;
import findupproducts.example.com.findup.UI.fragments.SearchFragment;
import findupproducts.example.com.findup.UI.fragments.StoreAccountHomeFragment;
import findupproducts.example.com.findup.models.CurrentLocation;

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

    private static List<Class> getHomeFragments() {
        List<Class> fragmentList = new ArrayList<>();
        fragmentList.add(MainFragment.class);
        fragmentList.add(MapFragment.class);
        fragmentList.add(SearchFragment.class);
        fragmentList.add(CategoryFragment.class);
        fragmentList.add(ProfileFragment.class);
        return fragmentList;
    }

    /*
        public static String getActiveHomeFragment(Context mContext){
            android.app.Fragment f = ((Activity)mContext).getFragmentManager().findFragmentById(R.id.main_toolbar_container);
            f.getTag();
        }
    */
    private static List<String> tags = new ArrayList<>();

    public static List<String> fragmentTagsList() {
        if (tags.size() > 1)
            return tags;
        else {
            tags.clear();
            tags.add(MainFragment.class.getName());
            tags.add(MapFragment.class.getName());
            tags.add(SearchFragment.class.getName());
            tags.add(CategoryFragment.class.getName());
            tags.add(ProfileFragment.class.getName());
            return tags;
        }

    }

    private static List<String> storeTags = new ArrayList<>();

    public static List<String> storeFragmentTagsList() {
        if (storeTags.size() > 1)
            return storeTags;
        else {
            storeTags.clear();
            storeTags.add(StoreAccountHomeFragment.class.getName());
            storeTags.add(ChatWithStoreFragment.class.getName());
            storeTags.add(ProfileStoreFragment.class.getName());
            return storeTags;
        }

    }

    public static void replaceFragment(FragmentManager manager, Fragment fragment, int containerID, int transition, Menu menu) {
        String fragmentTag = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(fragmentTag, 0);
        if (menu != null) {
            fragmentTagsList();
            UI_Utility.BottomNavigationMenu_icons_change(menu, tags.indexOf(fragmentTag));
        }

        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
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

    public static void replaceStoreFragment(FragmentManager manager, Fragment fragment, int containerID, int transition, Menu menu) {
        String fragmentTag = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(fragmentTag, 0);
        if (menu != null) {
            storeFragmentTagsList();
            UI_Utility.BottomNavigationStoreMenu_icons_change(menu, storeTags.indexOf(fragmentTag));
        }

        FragmentTransaction ft = manager.beginTransaction();
        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
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

    private static String getFragmentTag(Fragment fragment) {
        if (fragment instanceof MapFragment)
            return "map";
        else if (fragment instanceof SearchFragment)
            return "search";
        else if (fragment instanceof CategoryFragment)
            return "cat";
        else if (fragment instanceof ProfileFragment)
            return "profile";
        else if (fragment instanceof MainFragment)
            return "main";
        else return "main";
    }

    public static void UpdateCurrentLocation(final Activity activity, final Context context) {
        Dexter.withActivity(activity)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            LocationManager mLocationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);

                            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }
                            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000,
                                    1000, new LocationListener() {
                                        @Override
                                        public void onLocationChanged(Location location) {
                                            if (location != null) {
                                                SharedPrefManger sharedPrefManger = new SharedPrefManger(activity);
                                                CurrentLocation currentLocation = new CurrentLocation(location.getLatitude(), location.getLongitude());
                                                sharedPrefManger.setCurrentLocation(currentLocation);
                                            }
                                        }

                                        @Override
                                        public void onStatusChanged(String provider, int status, Bundle extras) {

                                        }

                                        @Override
                                        public void onProviderEnabled(String provider) {
                                            // Toast.makeText(context, "من فضلك تأكد من الموافقه علي صلاحيات الوصول الي احداثيات موقعك", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onProviderDisabled(String provider) {
                                            // Toast.makeText(context, "من فضلك تأكد من الموافقه علي صلاحيات الوصول الي احداثيات موقعك", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(context, "من فضلك تأكد من الموافقه علي صلاحيات الوصول الي احداثيات موقعك", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }


    public static void sendEmail(Context mContext, String email) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        i.putExtra(Intent.EXTRA_SUBJECT, mContext.getResources().getString(R.string.email_title_to_store));
        try {
            mContext.startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void OpenWebSite(Context mContext, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
        mContext.startActivity(browserIntent);
    }

    public static void OpenChatWithStore(Context mContext, String store_id, String name) {
        Intent intent = new Intent(mContext, SpecificChatWithStore.class);
        intent.putExtra("store_id", store_id);
        intent.putExtra("store_name", name);
        mContext.startActivity(intent);
    }

    public static void OpenTwitterAccount(Context mContext, String username) {
        Intent intent = null;
        /*try {
            // get the Twitter app if possible
             mContext.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=USERID"));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/PROFILENAME"));
        }*/
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + username));
        mContext.startActivity(intent);
    }

    public static void OpenSnapChatAccount(Context mContext, String username) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + username));
            intent.setPackage("com.snapchat.android");
            mContext.startActivity(intent);
        } catch (Exception e) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + username)));
        }
    }

    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        context.startActivity(intent);
    }

    public static void OpenGoogleMaps(Context mContext, double latitude, double longitude) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mContext.startActivity(intent);
    }
}