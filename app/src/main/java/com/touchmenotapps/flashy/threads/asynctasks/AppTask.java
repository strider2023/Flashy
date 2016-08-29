package com.touchmenotapps.flashy.threads.asynctasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.touchmenotapps.flashy.dao.enums.DeviceType;
import com.touchmenotapps.flashy.dao.enums.OSType;
import com.touchmenotapps.flashy.dao.enums.ServerEvents;
import com.touchmenotapps.flashy.dao.interfaces.ServerResponseListener;
import com.touchmenotapps.flashy.utils.AppPreferences;
import com.touchmenotapps.flashy.utils.NetworkUtils;

import org.json.simple.parser.JSONParser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by arindamnath on 10/02/16.
 */
public abstract class AppTask extends AsyncTask<Object, Void, ServerEvents> {

    private int id;
    private ServerResponseListener serverResponseListener;
    private NetworkUtils networkUtils;
    private AppPreferences appPreferences;
    private JSONParser parser;
    private ProgressDialog mProgress;
    private Context context;
    private TelephonyManager telephonyManager;

    public AppTask(int id, Context context, ServerResponseListener serverResponseListener){
        this.id = id;
        this.context = context;
        this.serverResponseListener = serverResponseListener;
        this.networkUtils = new NetworkUtils(context);
        this.appPreferences = new AppPreferences(context);
        this.mProgress = new ProgressDialog(context, AlertDialog.THEME_HOLO_LIGHT);
        this.mProgress.setMessage("Loading...");
        this.mProgress.setCancelable(false);
        this.parser = new JSONParser();
        this.telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgress.show();
    }

    @Override
    protected void onPostExecute(ServerEvents serverEvents) {
        super.onPostExecute(serverEvents);
        mProgress.dismiss();
    }

    public int getId() {
        return id;
    }

    public ServerResponseListener getServerResponseListener() {
        return serverResponseListener;
    }

    public NetworkUtils getNetworkUtils() {
        return networkUtils;
    }

    public AppPreferences getAppPreferences() {
        return appPreferences;
    }

    public JSONParser getParser() {
        return parser;
    }

    public ProgressDialog getmProgress() {
        return mProgress;
    }

    public Context getContext() {
        return context;
    }

    private DeviceType isTabletDevice(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE);
        if (xlarge) {
            DisplayMetrics metrics = new DisplayMetrics();
            Activity activity = (Activity) context;
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT
                    || metrics.densityDpi == DisplayMetrics.DENSITY_HIGH
                    || metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM
                    || metrics.densityDpi == DisplayMetrics.DENSITY_TV
                    || metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
                return DeviceType.TABLET;
            }
        }
        return DeviceType.PHONE;
    }

    protected Map<String,Object> getDeviceInfo(final Context context) {
        Map<String,Object> device = new HashMap<>();
        device.put("deviceId", getDeviceId(context));
        device.put("osType", OSType.ANDROID.toString());
        device.put("osVersion", Build.VERSION.SDK_INT);
        device.put("deviceType", isTabletDevice(context).toString());
        device.put("model", Build.MODEL);
        device.put("device_name", Build.DEVICE);
        device.put("manufacturer", Build.MANUFACTURER);
        device.put("brand", Build.BRAND);
        device.put("language", Locale.getDefault().getDisplayLanguage());
        device.put("operator", telephonyManager.getNetworkOperatorName());
        try {
            device.put("imei", telephonyManager.getDeviceId().toUpperCase());
        } catch (Exception e) {
            device.put("imei", Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID).toUpperCase());
        }
        return device;
    }

    private String getDeviceId(Context context) {
        return md5(Settings.Secure.getString(
                context.getContentResolver(),
                Settings.Secure.ANDROID_ID))
                .toUpperCase();
    }

    private final String md5(final String s) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
