package com.willkong.androidutils.library;

import android.util.Base64;
import android.util.Log;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESUtil {
    // 密钥 长度不得小于24
    private final static String secretKey = "sziov@shznetme@201801164" ;
    // 向量 可有可无 终端后台也要约定
    private final static String iv = "12234567" ;
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8" ;

    /**
     * 3DES加密
     *
     * @param plainText
     *            普通文本
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null ;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey .getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec( iv.getBytes());
        cipher.init(Cipher. ENCRYPT_MODE , deskey, ips);
        byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding ));
        return Base64.encodeToString(encryptData, Base64. DEFAULT );
    }

    /**
     * 3DES解密
     *
     * @param encryptText
     *            加密文本
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText) throws Exception {
        Key deskey = null ;
        DESedeKeySpec spec = new DESedeKeySpec( secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "desede" );
        deskey = keyfactory. generateSecret(spec);
        Cipher cipher = Cipher.getInstance( "desede/CBC/PKCS5Padding" );
        IvParameterSpec ips = new IvParameterSpec( iv.getBytes());
        cipher. init(Cipher. DECRYPT_MODE, deskey, ips);

        byte [] decryptData = cipher.doFinal(Base64. decode(encryptText, Base64. DEFAULT));

        return new String(decryptData, encoding);
    }

    public static void DESUtilTest(){
        String tesult= null;
        try {
            tesult = encode("1234567890");
            Log.d("DESUtil",tesult);
            Log.d("DESUtil","解密后："+ decode(tesult) );
        } catch (Exception e) {
            e.printStackTrace();
        }

       ;


    }
}
