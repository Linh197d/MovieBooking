package com.base.moviebooking.utils;

import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class MyUtils {
    private static MyUtils instance = null;

    private Context context;
    public static MyUtils getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new MyUtils();
            return instance;
        }
    }

    public static final String ALIAS = "MovieBookingKey";

    public void createKey() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, CertificateException, IOException {

        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

        keyStore.load(null);

        if (!keyStore.containsAlias(ALIAS)) {

            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keyGenerator.init(new KeyGenParameterSpec.Builder(ALIAS,

                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)

                        .setBlockModes(KeyProperties.BLOCK_MODE_CBC)

                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)

                        .setRandomizedEncryptionRequired(false)

                        .build());
            }

            keyGenerator.generateKey();
            if (keyStore.containsAlias(ALIAS)) {

                System.out.println("Khóa đã được tạo thành công.");

            } else {

                System.out.println("Khóa chưa được tạo. Cần gọi createKey().");

            }
        }

    }
    public String encryptData(String plaintext) throws KeyStoreException,

            UnrecoverableEntryException, NoSuchAlgorithmException, NoSuchPaddingException,

            InvalidKeyException, IOException, BadPaddingException,

            IllegalBlockSizeException, CertificateException {

        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

        keyStore.load(null);

        SecretKey secretKey = ((KeyStore.SecretKeyEntry) keyStore.getEntry(ALIAS, null)).getSecretKey();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] ivBytes = cipher.getIV();

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes("UTF-8"));



        // Prepend IV to data for later retrieval

        return Base64.encodeToString(ivBytes, Base64.DEFAULT) +

                ":" +

                Base64.encodeToString(encryptedBytes, Base64.DEFAULT);

    }
    public String decryptData(String ciphertext) throws KeyStoreException,

            UnrecoverableEntryException, NoSuchAlgorithmException, NoSuchPaddingException,

            InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException,

            InvalidAlgorithmParameterException, CertificateException {

        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");

        keyStore.load(null);

        SecretKey secretKey = ((KeyStore.SecretKeyEntry) keyStore.getEntry(ALIAS, null)).getSecretKey();

        String[] parts = ciphertext.split(":");

        byte[] iv = Base64.decode(parts[0], Base64.DEFAULT);

        byte[] cipherText = Base64.decode(parts[1], Base64.DEFAULT);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

        byte[] decryptedBytes = cipher.doFinal(cipherText);

        return new String(decryptedBytes, "UTF-8");

    }
}
