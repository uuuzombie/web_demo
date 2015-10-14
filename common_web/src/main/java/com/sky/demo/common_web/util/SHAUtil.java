package com.sky.demo.common_web.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 2015/7/15.
 * SHA加密
 */
public class SHAUtil {

    private static final Logger logger = LoggerFactory.getLogger(SHAUtil.class);

    public static String encrypt(String str) throws UnsupportedEncodingException {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            logger.error("MessageDigest get error",e);
            return "";
        }

        byte[] byteArray = str.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static void main(String[] args) {
        String str = "abc";
        String encrypt = null;
        try {
            encrypt = SHAUtil.encrypt(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(encrypt);
    }
}
