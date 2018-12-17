package findupproducts.example.com.findup.netHelper;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

import findupproducts.example.com.findup.CONST;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.Utils;

/**
 * Created by Taha on 3/10/2018.
 */

public class MakeRequest
{


    String BaseUrl =  CONST.API_FILE_DOMAIN  ; //www.example.com

    String Sub_Url ; //.../example/example

    String Type ;    // post Or Get   1 for POST 0 For GET

    String cUrl ;  //Base+Sub

    Map <String , String> map = new HashMap <String , String >() ;  //parameter send to server

    Activity context ;
    Map<String , String> Responce = new HashMap<String, String>();

    String funName ;
    Boolean flag = false ;




    public MakeRequest(String sub_Url , String Type , Map<String ,String> map,Activity context , String funName , Boolean flag)
    {

        this.Sub_Url =sub_Url ;
        this.Type = Type ;
        this.map = map ;
        this.context = context ;
        cUrl = BaseUrl.concat(Sub_Url);
        this.flag = flag ;
        this.funName = funName ;


    }


    public MakeRequest(String sub_Url , String Type , Activity context , String funName ,  Boolean flag )
    {


        this.Sub_Url =sub_Url ;
        this.Type = Type ;
        this.map = map ;
        this.context = context ;
        this.funName = funName ;
        this.flag = flag ;

    }



    public void request (final VolleyCallback callback /*, final ONRetryHandler onRetryHandler */, final OnCancelRetry onCancelRetry)
    {



        if(!Utils.isOnline(context))
        {


        }
        else
            {
                final AlertDialog progressDialog = UI_Utility.ShowProgressDialog(context, flag);
                progressDialog.setCancelable(false);
                if(flag)
                progressDialog.show();
                StringRequest stringRequest = new StringRequest(Integer.valueOf(Type),Sub_Url , new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Responce.put("status" , "ok");
                        Log.e(funName , response);
                        Responce.put("res" , response);
                        progressDialog.dismiss();
                        callback.onSuccess(Responce);
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progressDialog.dismiss();
                        if(flag)
                        {

                        }//else onCancelRetry.OnCacelRetry();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params = map ;
                        return params;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy( 30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                VolleySinglton.getInstance(context).addToRequestQueue(stringRequest);
            }



    }


}
