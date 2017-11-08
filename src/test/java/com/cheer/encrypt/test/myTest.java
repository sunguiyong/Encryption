/*
 * 文件名：myTest.java
 * 版权：Copyright by www.cheer.com
 * 描述：
 * 修改人：Administrator
 * 修改时间：2017年10月29日
 */

package com.cheer.encrypt.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class myTest
{
    private static final Logger LOGGER = LogManager.getLogger(myTest.class);

    @Test
    public void test1() throws Exception // nosuchalgolthmexception
    {
        String signString = "Thank you!";

        String type = "SHA-1";
        String result = sign(signString, type);
        System.out.println("采用"+ type + "加密之后的串为：" + result);

        type = "MD5";
        result = sign(signString, type);
        System.out.println("采用" + type + "加密之后的串为：" + result);
    }

    public static String sign(String str, String type)
    {
        String s = encrypt(str, type);
        return s;
    }

    public static String encrypt(String strSrc, String encName) 
    {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try 
        {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); 
        } 
        catch (NoSuchAlgorithmException e) 
        {
            System.out.println("签名失败！");
            return null;
        }

        return strDes;
    }

    public static String bytes2Hex(byte[] bts) 
    {
        String des = "";
        String tmp = null;

        for (int i = 0; i < bts.length; i++) 
        {
            tmp = (Integer.toHexString(bts[i] & 0xFF));

            if (tmp.length() == 1) 
            {
                des += "0";
            }
            des += tmp;
        }

        return des;
    }
}
