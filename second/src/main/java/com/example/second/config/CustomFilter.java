package com.example.second.config;


import com.example.second.dao.UserDao;
import com.example.second.entity.RespBean;
import com.example.second.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomFilter extends GenericFilter {
    @Autowired
    UserDao userDao;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if ("POST".equals(req.getMethod()) && "/doLogin".equals(req.getServletPath())) {
            //登录请求
            String company = req.getParameter("company");
            String username = req.getParameter("username");
            User userData = userDao.findCompanyByUsername(username, company);
            if (userData == null) {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                String s = new ObjectMapper().writeValueAsString(RespBean.error("单位账号填写错误"));
                out.write(s);
                out.flush();
                out.close();
            } else {
                chain.doFilter(req, resp);
            }
            String code = req.getParameter("code");
            String verify_code = (String) req.getSession().getAttribute("verify_code");
            if (code == null || "".equals(code) || !verify_code.toLowerCase().equals(code.toLowerCase())) {
                //验证码不正确
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                String s = new ObjectMapper().writeValueAsString(RespBean.error("验证码填写错误"));
                out.write(s);
                out.flush();
                out.close();
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }
}
