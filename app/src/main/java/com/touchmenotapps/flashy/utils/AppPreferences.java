package com.touchmenotapps.flashy.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private SharedPreferences mAppPrefs;

    public AppPreferences(Context context) {
        mAppPrefs = context.getSharedPreferences("AppPreferences", 0);
    }

    public void saveUserInfo(String name, String email, String phone, String password) {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        String[] userName = name.split("\\s+");
        if(userName.length == 1) {
            edit.putString("userFirstName", userName[0]);
        } else if(userName.length == 2) {
            edit.putString("userFirstName", userName[0]);
            edit.putString("userLastName", userName[1]);
        } else if(userName.length > 2) {
            edit.putString("userFirstName", userName[0]);
            edit.putString("userMiddleName", userName[1]);
            edit.putString("userLastName", userName[2]);
        }
        edit.putString("userEmail", email);
        edit.putString("userPhone", phone);
        edit.commit();
    }

    public void saveSessionToken(String token) {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putString("sessionToken", token);
        edit.commit();
    }

    public String getUserFirstName() {
        return mAppPrefs.getString("userFirstName", null);
    }

    public String getUserMiddleName() {
        return mAppPrefs.getString("userMiddleName", null);
    }

    public String getUserLastName() {
        return mAppPrefs.getString("userLastName", null);
    }

    public String getUserEmail() {
        return mAppPrefs.getString("userEmail", null);
    }

    public String getUserPhone() {
        return mAppPrefs.getString("userPhone", null);
    }

    public void setLoggedIn() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putBoolean("appLoggedIn", true);
        edit.commit();
    }

    public void setLoggedOut() {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putBoolean("appLoggedIn", false);
        edit.putString("userFirstName", null);
        edit.putString("userMiddleName", null);
        edit.putString("userLastName", null);
        edit.putString("userEmail", null);
        edit.putString("userPhone", null);
        edit.commit();
    }

    public boolean isUserLoggedIn() {
        return mAppPrefs.getBoolean("appLoggedIn", false);
    }
}
