package com.base.mvvmbaseproject;

import android.content.Context;

public class DataLocalManager {
    private static final String PREF_API= "PREFER_API";
    private static DataLocalManager instance;
    private MySharePreferences mySharePreferences;
    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharePreferences = new MySharePreferences(context);

    }
    public static DataLocalManager getInstance(){
        if(instance ==null){
            instance = new DataLocalManager();
        }
        return instance;
    }
    public static void setAccessToken(String token){
        DataLocalManager.getInstance().mySharePreferences.putStringValue("acessToken",token);
    }
    public static String getAccessToken(){
       return DataLocalManager.getInstance().mySharePreferences.getStringValue("acessToken");
    }
    public static void setBooleanValue(boolean value){
        DataLocalManager.getInstance().mySharePreferences.putBooleanValue("dataluu",value);
    }
    public static boolean getBooleanValue(){
       return DataLocalManager.getInstance().mySharePreferences.getBooleanValue("dataluu");
    }
}
