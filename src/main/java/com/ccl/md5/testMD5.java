package com.ccl.md5;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by ccl on 16/9/6.
 */
public class testMD5 {
    @Test
    public void testmd5() {
        StringBuffer sb = new StringBuffer(32);

        try {
            String source = "P6Zetw7u";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3));
            }
            System.out.println(sb.toString());
        } catch (Exception e) {

        }
    }

    @Test
    public void testoldmd5(){
        String str = "P6Zetw7u";
        String result = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            result = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test
    public void test(){
        testmd5();
        testoldmd5();
    }
}
