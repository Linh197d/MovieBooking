package com.base.mvvmbaseproject;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferences {
    private static final String My_share_prefer= "MY_SHARE_PREFER";
    private Context mcontext;
    public MySharePreferences(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void putBooleanValue(String key,boolean value){
        SharedPreferences sharePreferences= mcontext.getSharedPreferences(My_share_prefer,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(My_share_prefer,Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(key,false);
    }
    public void putStringValue(String key,String value){
        SharedPreferences sharePreferences= mcontext.getSharedPreferences(My_share_prefer,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getStringValue(String key){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(My_share_prefer,Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,"");
    }
}
