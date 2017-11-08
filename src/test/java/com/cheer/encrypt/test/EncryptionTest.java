/*
 * 文件名：EncryptionTest.java 版权：Copyright by www.cheer.com 描述： 修改人：Administrator 修改时间：2017年10月29日
 */

package com.cheer.encrypt.test;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.cheer.encrypt.helloworld.Login;


public class EncryptionTest
{
    private static final Logger LOGGER = LogManager.getLogger(EncryptionTest.class);

    private static SessionFactory sf;

    private Session session;

    private Transaction tx;

    // 单个测试类只执行一次
    @BeforeClass
    public static void init()
    {
        // 创建SessionFactory
        sf = new MetadataSources(new StandardServiceRegistryBuilder().configure(
            "hibernate.cfg.xml").build()).buildMetadata().buildSessionFactory();
    }

    // 单个测试类只执行一次
    @AfterClass
    public static void destory()
    {
        // 关闭SessionFactory
        sf.close();
    }

    // 执行每个测试方法前都会执行
    @Before
    public void before()
    {
        // 获取Session（注：此时的session不需要关闭）
        this.session = sf.getCurrentSession();

        // 获取并开启事务
        tx = this.session.beginTransaction();
    }

    @Test
    public void inPW()
    {
        Login login = new Login();
        login.setPassword(jdkMd5());
        login.setUsername("sunguiyong");
        this.session.save(login);
        tx.commit();
    }

    public String jdkMd5()
    {
        String str = "mynameischeer";
        try
        {
            // 获取该类所实现的加密方法MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 此方法把字符串变为byte数组形式
            byte[] md5Bytes = md.digest(str.getBytes());

            // 把数组转换成16进制的字符串
            str = Hex.encodeHexString(md5Bytes);
            System.out.println("jdkMD5 :" + Hex.encodeHexString(md5Bytes));

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        // 返回str值
        return str;

    }

}
