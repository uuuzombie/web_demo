package com.sky.demo.common_web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by rg on 10/18/15.
 */
@RequestMapping("/common/code")
@Controller
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);


    //方式一，设置response，返回json，解决中文乱码
    @RequestMapping("/getFoo1")
    public void getFoo1(@RequestParam int id,HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        Map<String,Object> ret = Maps.newHashMap();
        ret.put("id",id);
        ret.put("name","张三");

        String result = "";
        PrintWriter printWriter = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(ret);

            printWriter = response.getWriter();
            printWriter.print(result);
            printWriter.flush();

        } catch (IOException e) {
            logger.error("error",e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    //方式二，设置OutputStream，返回json，解决中文乱码
    @RequestMapping("/getFoo2")
    public void getFoo2(@RequestParam int id,HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> ret = Maps.newHashMap();
        ret.put("id",id);
        ret.put("name","张三");

        String result = "";
        ServletOutputStream os = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(ret);

            os = response.getOutputStream();
            os.write(result.getBytes(Charset.forName("UTF-8")));
            os.flush();

        } catch (IOException e) {
            logger.error("error",e);
        }
    }


    //方式三，设置produces，返回json，解决中文乱码
    @RequestMapping(value = "/getFoo3",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getFoo3(@RequestParam int id,HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> ret = Maps.newHashMap();
        ret.put("id",id);
        ret.put("name","张三");

        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(ret);
        } catch (IOException e) {
            logger.error("error",e);
        }
        return result;
    }

    //返回json，解决中文乱码
    @RequestMapping("/getFoo4")
    @ResponseBody
    public Map<String,Object> getFoo4(@RequestParam int id,HttpServletRequest request, HttpServletResponse response) {

        Map<String,Object> ret = Maps.newHashMap();
        ret.put("id",id);
        ret.put("name","张三");

        return ret;
    }




}
