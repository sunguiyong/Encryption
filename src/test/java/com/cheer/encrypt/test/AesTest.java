/*
 * 文件名：AesTest.java 
 * 版权：Copyright by www.cheer.com 
 * 描述： AES
 * 修改人：Administrator 修改时间：2017年10月29日
 */

package com.cheer.encrypt.test;


import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class AesTest
{
    private static String str = "my name is cheer";

    public static void main(String[] args)
    {
        jdkAes();
    }

    public static void jdkAes()
    {

        try
        {

            // 得到KEY的生成器AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // key的size
            keyGenerator.init(128);
            // 生成KEY
            SecretKey secretKey = keyGenerator.generateKey();
            // 得到KEY的byte数组
            byte[] keyBytes = secretKey.getEncoded();

            // KEY转换,securate,
            Key key = new SecretKeySpec(keyBytes, "AES");

            // Cihper(密码)
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(str.getBytes());
            System.out.println("jdk AES encrypt :" + Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("jdk AES decrypt :" + new String(result));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchPaddingException e)
        {
            e.printStackTrace();
        }
        catch (InvalidKeyException e)
        {
            e.printStackTrace();
        }
        catch (IllegalBlockSizeException e)
        {
            e.printStackTrace();
        }
        catch (BadPaddingException e)
        {
            e.printStackTrace();
        }

    }

}
