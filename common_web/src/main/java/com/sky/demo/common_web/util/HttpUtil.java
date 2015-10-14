package com.sky.demo.common_web.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by rg on 2015/7/21.
 */
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * 获取用户ip地址
     * @param request
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            return ip;
        }

        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(ip) && !StringUtils.equalsIgnoreCase("unknown", ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }


    /**
     * 获取服务端ip
     * @return
     */
    public static String getLocalIp() {
        String ipAddrStr = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddrStr = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("get local ip error",e);
        }
        return ipAddrStr;
    }
}
