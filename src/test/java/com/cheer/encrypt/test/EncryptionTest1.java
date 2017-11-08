/*
 * 文件名：EncryptionTest1.java 版权：Copyright by www.cheer.com 描述： 修改人：Administrator 修改时间：2017年10月29日
 */

package com.cheer.encrypt.test;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


public class EncryptionTest1
{
    private static String str = "my name is cheer";

    public static void main(String[] args)
    {
        // jdkMd5();
        ccBase64();
    }

    public static void jdkMd5()
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(str.getBytes());
            str = Hex.encodeHexString(md5Bytes);
            System.out.println(str);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    public static void ccBase64()
    {
        byte[] encode = Base64.encodeBase64(str.getBytes());
        System.out.println("encode :" + new String(encode));

        byte[] decode = Base64.decodeBase64(encode);
        System.out.println("decode :" + new String(decode));

    }

}
