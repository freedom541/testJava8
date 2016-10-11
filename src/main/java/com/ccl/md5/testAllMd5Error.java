package com.ccl.md5;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by ccl on 16/9/6.
 */
public class testAllMd5Error {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 1000000; i++) {
            String pwd = ShortUUID.getMiniUuid();
            String newPwd = getMd5Pwd(pwd);
            if (newPwd.length() != 32){
                //throw  new RuntimeException("error: " +   pwd + "===========" + newPwd);
                System.out.println("error: " +   pwd + "===========" + newPwd);
            }else {
                System.out.println(pwd + "===========" + newPwd);
            }
        }
    }

    public static String getMd5Pwd(String str) {

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
        return result;
    }
}
