package com.ccl.md5;

import java.security.MessageDigest;

/**
 * Created by ccl on 16/9/6.
 */
public class testAllMd5 {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10000000; i++) {
            String pwd = ShortUUID.getMiniUuid();
            String newPwd = getMd5Pwd(pwd);
            if (newPwd.length() != 32){
                throw  new RuntimeException("error: " +   pwd + "===========" + newPwd);
            }else {
                System.out.println(pwd + "===========" + newPwd);
            }
        }
    }

    public static String getMd5Pwd(String source) {

        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(source.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }
}
