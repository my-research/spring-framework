package com.github.dhslrl321.servlet;

import com.github.dhslrl321.servlet.HelloServlet;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyServletContainer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        /*context.addServlet("helloServlet", new HelloServlet())
                .addMapping("/hello");

        context.addServlet("byeServlet", new ByeServlet())
                .addMapping("/bye");*/

        context.addServlet("dispatcher", new DispatcherServlet())
                .addMapping("/*");
    }
}
