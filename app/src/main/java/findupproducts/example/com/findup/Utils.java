package findupproducts.example.com.findup;

import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Taha Elhosseny on 2/4/2018.
 */
public class Utils {


    public static Boolean IsDev = false ;


    public static String DevUrl = "https://apidev.foreraa.com/v3/" ;
    public static String ReleaseUrl = "https://api.foreraa.com/v3/" ;


    public static boolean isOnline(Context con){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo =connectivityManager.getActiveNetworkInfo();
        if(netInfo!=null) {

            if (netInfo.isConnected()){
                connected=true;
            }
        }
        return connected;
    }

    static public void changeLocale(Context con , String language ){
        Resources res = con.getResources();
        Configuration config = res.getConfiguration();
        if(language==null ||language.length()==0){
            config.locale= Locale.getDefault();
        }else {
            config.locale=new Locale(language);
        }
        Log.d("language",""+config.locale);
        res.updateConfiguration(config,null);
    }

    static public Typeface changeFont(Context con, int loc){
        Typeface typeface = null;
        if(loc ==0){
            typeface= Typeface.createFromAsset(con.getAssets(),"fonts/alfares.ttf");
        }else {
            typeface= Typeface.createFromAsset(con.getAssets(), "fonts/alfares.ttf");
        }
        return typeface;
    }

    static public Typeface setFontawesome(Context context){
        return  Typeface.createFromAsset(context.getAssets(),"fonts/fontawesome-webfont.ttf");
    }

    public static boolean permissionCheck(Activity activity, String permission) {
        if(ContextCompat.checkSelfPermission(activity ,permission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else
            return false;
    }

    public static void permissionGrant(Activity activity,String permission,int permissionCode){
        ActivityCompat.requestPermissions(activity,new String[]{permission},permissionCode);
    }




    static public String convertListOfStringToString( List<String> list){
        Type listsType = new TypeToken<List<String>>() {
        }.getType();
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(list, listsType);
        JsonArray jsonArray = element.getAsJsonArray();
        //Log.d(TAG, jsonArray.toString());
        return jsonArray.toString();

    }

    static public  ArrayList<String> getListOFStringFromString2(Context context,String text)
    {

        Type listsType = new TypeToken<List<String>>()
        {
        }.getType();
        Gson gson = new Gson();
        String itemsList = text;
//        Log.d("listStrings", itemsList);
        return gson.fromJson(itemsList, listsType);
    }


    static public  List<String> getListOFStringFromString(Context context,String text)
    {

        Type listsType = new TypeToken<List<String>>()
        {
        }.getType();
        Gson gson = new Gson();
        String itemsList = text;
        Log.d("listStrings", itemsList);
        return gson.fromJson(itemsList, listsType);
    }


    public static Bitmap getRoundedShape(Bitmap bitmap) {
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                    (Math.min(((float) bitmap.getWidth()), ((float) bitmap.getHeight())) / 2), paint);
            Log.d("w_h",bitmap.getWidth()+" , "+bitmap.getWidth() + " , "+(Math.min(((float) bitmap.getWidth()), ((float) bitmap.getHeight())) / 2));
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;

    }

   /* public static String changNumToArabic(String input, KiwihatParameters p) {
        if (p.getInt("language",0) == 0) // arabic{
        {
            char[] arabicChars = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i))) {
                    try {
                        builder.append(arabicChars[(int) (input.charAt(i)) - 48]);
                    } catch (Exception e) {
                    }

                } else {
                    builder.append(input.charAt(i));
                }
            }


            if (builder.toString().length() > 0)
                return builder.toString();
            else
                return input;
        } else {
            return input;
        }


    }*/


    public static String getTime(String time){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(time));
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        if(hour>=12){
            if(hour==12){
                return (hour)+":"+minute+ "PM";
            }
            return (hour-12)+":"+minute+ "PM";
        }else {
            if(hour==0)
                return (12)+":"+minute+ "AM";

            return (hour)+":"+minute+ "AM";
        }


    }

    public static String [] getDate2(String time)
    {
        String [] str =new String [3] ;
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(time));
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);

        str[0] = day + "" ;
        str[1] = (month+ 1 )+ "";
        str[2] = year + "" ;

        return str;


    }


    public static String getDate(String time){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(time));
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);

        return day+"/"+(month+1)+"/"+year;


    }







   /* public static String changNumToArabicc(String input)
    {
            char[] arabicChars = {'٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩'};
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (Character.isDigit(input.charAt(i))) {
                    try {
                        builder.append(arabicChars[(int) (input.charAt(i)) - 48]);
                    } catch (Exception e) {
                    }

                } else {
                    builder.append(input.charAt(i));
                }
            }


            if (builder.toString().length() > 0)
                return builder.toString();
            else
                return input;
        }*/

    public static String changNumToArabicc(String input)
    {
        return input ;
    }



    public static String getKiwihatParentURL()
    {


        if(IsDev)
        {
            if(BuildConfig.DEBUG)
            {
                Log.e("link is state 1 " , DevUrl ) ;
                return DevUrl ;
            }else
            {
                Log.e("link is state 2 " , ReleaseUrl ) ;

                return ReleaseUrl ;
            }
        }else
            {
                if(BuildConfig.DEBUG)
                {
                    Log.e("link is state 3 " , DevUrl ) ;

                    return DevUrl ;
                }else
                {
                    Log.e("link is state 4 " , ReleaseUrl ) ;

                    return ReleaseUrl ;
                }
            }
    }




}
