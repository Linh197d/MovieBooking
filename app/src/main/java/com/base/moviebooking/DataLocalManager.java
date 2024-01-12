package com.base.moviebooking;

import android.content.Context;
import android.util.Log;

import com.base.moviebooking.utils.MyUtils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class DataLocalManager {
    private static final String PREF_API = "PREFER_API";
    private static DataLocalManager instance;
    private MySharePreferences mySharePreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharePreferences = new MySharePreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setAccessToken(String token)  {
//        token =  MyUtils.getInstance().encryptData(token);
//        Log.d("linhd","token encrypte:"+token);
        DataLocalManager.getInstance().mySharePreferences.putStringValue("acessToken", token);
    }

    public static String getAccessToken() {
        return DataLocalManager.getInstance().mySharePreferences.getStringValue("acessToken");

//        try {
//            Log.d("linhd","gettoken decrypte:"+ MyUtils.getInstance().decryptData(s));
//            return MyUtils.getInstance().decryptData(s);
//        } catch (KeyStoreException e) {
//            throw new RuntimeException(e);
//        } catch (UnrecoverableEntryException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchPaddingException e) {
//            throw new RuntimeException(e);
//        } catch (InvalidKeyException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (BadPaddingException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalBlockSizeException e) {
//            throw new RuntimeException(e);
//        } catch (InvalidAlgorithmParameterException e) {
//            throw new RuntimeException(e);
//        } catch (CertificateException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void setBooleanValue(boolean value) {
        DataLocalManager.getInstance().mySharePreferences.putBooleanValue("dataluu", value);
    }

    public static boolean getBooleanValue() {
        return DataLocalManager.getInstance().mySharePreferences.getBooleanValue("dataluu");
    }
    public static void setStringValue(String value) throws UnrecoverableEntryException, NoSuchPaddingException, IllegalBlockSizeException, CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        value = MyUtils.getInstance().encryptData(value);
        Log.d("linhd","value encrypte:"+value);
        DataLocalManager.getInstance().mySharePreferences.putStringValue("dataString", value);
    }

    public static String getStringValue() {
        String s = DataLocalManager.getInstance().mySharePreferences.getStringValue("dataString");
        try {
            Log.d("linhd","getvalue decrypte:"+ MyUtils.getInstance().decryptData(s));
            return MyUtils.getInstance().decryptData(s);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableEntryException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }

    }
}
