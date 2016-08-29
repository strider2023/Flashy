package com.touchmenotapps.flashy.utils;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

public class NetworkUtils {

	private Context context;

	public NetworkUtils(Context context) {
		this.context = context;
	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

    public void showNetworkErrorDialog() {
            final AlertDialog networkErrorAlertDialog = new AlertDialog.Builder(context, 
                            AlertDialog.THEME_DEVICE_DEFAULT_DARK).create();
            networkErrorAlertDialog.setCanceledOnTouchOutside(false);
            networkErrorAlertDialog.setTitle("Network Error");
            networkErrorAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Settings",
                            new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            networkErrorAlertDialog.dismiss();
                    }
            });
            
            networkErrorAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                            new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            networkErrorAlertDialog.dismiss();
                    }
            });
            networkErrorAlertDialog.show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	@SuppressWarnings("deprecation")
	public HttpURLConnection getHttpURLConInstance(String mURL, boolean isGet) throws Exception {
	    	URL url = new URL(mURL);
	    	HttpURLConnection mHttpPost= null;
	    	String proxyString = null;
	    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
	    		proxyString  = Settings.Secure.getString(context.getContentResolver(),
	                    Settings.Global.HTTP_PROXY);
	    	} else
	    		proxyString  = Settings.Secure.getString(context.getContentResolver(),
	                    Settings.Secure.HTTP_PROXY);
	    	//If there is a proxy
	    	if (proxyString != null)  
				mHttpPost = (HttpURLConnection) url.openConnection(
						new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
						proxyString.split(":")[0],
						Integer.parseInt(proxyString.split(":")[1]))));
			else
				mHttpPost = (HttpURLConnection) url.openConnection();
	    	
	    	mHttpPost.setRequestProperty("User-Agent", "Mozilla/5.0");
	    	if(isGet)
	    		mHttpPost.setRequestMethod("GET");
	    	else {
	    		mHttpPost.setDoInput(true);
	        	mHttpPost.setDoOutput(true);
	        	mHttpPost.setRequestMethod("POST");
	        	mHttpPost.setRequestProperty("Content-Type", "application/json"); 
	    	}
	    	
	    	return mHttpPost;
    }
}
