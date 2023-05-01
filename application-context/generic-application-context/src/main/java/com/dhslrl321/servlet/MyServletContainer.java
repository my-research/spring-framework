package com.dhslrl321.servlet;

import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyServletContainer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        context.addServlet("hello", new HelloServlet())
                .addMapping("/hello");
    }
}
