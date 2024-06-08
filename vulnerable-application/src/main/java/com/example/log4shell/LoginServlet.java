package com.example.log4shell;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import javax.servlet.http.HttpServletRequest; // 수정 필요

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("uname");
        String password = req.getParameter("password");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");

        if(userName.equals("admin") && password.equals("password")){
            out.println("Welcome Back Admin");
        }
        else{
            // 취약한 코드
            Logger logger = LogManager.getLogger(com.example.log4shell.log4j.class);
            logger.error(userName);

            out.println("<code> the password you entered was invalid, <u> we will log your information </u> </code>");
        }
    }

    @Override
    public void destroy() {
    }
}