package com.dhslrl321.servlet;

import com.dhslrl321.ApplicationContextHolder;
import com.dhslrl321.controller.HelloController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnnotationConfigApplicationContext applicationContext = ApplicationContextHolder.annotationConfigApplicationContext();
        HelloController controller = applicationContext.getBean(HelloController.class);
        resp.getWriter().println(controller.hello("hh"));
    }
}
