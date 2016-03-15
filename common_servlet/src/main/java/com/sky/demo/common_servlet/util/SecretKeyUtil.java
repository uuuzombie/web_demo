package com.sky.demo.common_servlet.util;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 读密钥配置文件
 * @author
 * 2013-7-31 下午04:41:24
 * TODO
 */
public class SecretKeyUtil {

	private static Logger logger = LoggerFactory.getLogger(SecretKeyUtil.class);
	public static String[] SKEYS;
	public static int decryptType = 0;
    public static final Locale locale = new Locale("zh", "CN");

	static {
		ResourceBundle rb = ResourceBundle.getBundle("sceretkey", locale);

		String skeysStr = rb.getString("skeys");
		decryptType = NumberUtils.toInt(rb.getString("decryptType"));
		if(null != skeysStr) {
			SKEYS = skeysStr.split(",");
			try {
				QueryCipherUtils.setSecretKeys(Arrays.asList(SKEYS));
			} catch (Exception e) {
				throw new RuntimeException("初始化密钥错误");
			}
		} else {
			throw new RuntimeException("加载密钥错误");
		}
	}
	
	public static String decrypt(String str, int index) {
		String d = "";
    	try {
    		long start = System.currentTimeMillis();
			QueryCipherUtils.EncryptItem data = QueryCipherUtils.decrypt(str, index);
			d = data.getQuery();
			long end = System.currentTimeMillis();
			logger.info("decrypt cost " + (end - start));
		} catch (Exception e) {
			logger.error("decrypt error",e);
			d = "";
		}
		return d;
	}
	
	public static String encrypt(String str, int index) throws Exception {
		return QueryCipherUtils.encrypt(str, index);
	}
	
	public static void main(String[] args) throws Exception {
		//System.out.println(URLEncoder.encode("2zGLjINp3R6wkbioBx2eBMgXCaHAjfE4RwBy0cHUBEw=", "UTF-8"));

//        String url = "flytype=2&fromCity=北京&toCity=香港&fromDate=2013-09-30&returnDate=2013-10-03&price=2520&tax=854&key=MU5164/MU595~MU596/MU5131&cabin=Y/R~R/Y";
//        System.out.println("url:" + url);
//        String encryptUrl = encrypt(url, 0);
//        System.out.println("encryptUrl:" + encryptUrl);

        //String encodeUrl = URLEncoder.encode(encryptUrl, "UTF-8");
        String encodeUrl = "nasGsPyZ/9zPiOGWZWpY1IY50cQGyXPIGzsZX19vqrb3k9pnTxxkpn" +
                "/Eyk1PoTM5uIpM6VQbAHeFS7sqZByHDVLEfT1YIjyEatULyzSjpp0W64WqJlKPcfQvog3+tbxFx5NHrM3Rrhk" +
                "//nSBVdYoCttss4nUdngZkvdPA2xJpVwSoUEQ9jgRiXEFHv5u1b3DV6cIBsbaq90OE5euKs/bAAaFFZ45XjFN";
        System.out.println("encodeUrl:" + encodeUrl);
//
//        String decodeUrl = URLDecoder.decode(encodeUrl,"UTF-8");
//        System.out.println("decodeUrl:" + decodeUrl);

        String decryptUrl = decrypt(encodeUrl,0);
        System.out.println("decryptUrl:" + decryptUrl);
    }
	
	
}
