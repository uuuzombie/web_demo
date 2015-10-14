package com.sky.demo.common_servlet.servlet;

import com.sky.demo.common_servlet.util.SecretKeyUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rg on 8/16/15.
 */
public class SecretKeyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String content = req.getParameter("content");
        String action = req.getParameter("action");

        if (StringUtils.isBlank(content)) {
            req.setAttribute("content", "");
            req.getRequestDispatcher("pages/secret_key.jsp").forward(req,resp);
            return;
        }

        try {
            if ("decode".equalsIgnoreCase(action)) {
                content = SecretKeyUtil.decrypt(content, 0);
            } else if ("encode".equalsIgnoreCase(action)){
                content = SecretKeyUtil.encrypt(content, 0);
            }

            req.setAttribute("content", content);
            req.getRequestDispatcher("pages/secret_key.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
