package com.dhslrl321.servlet;

import com.dhslrl321.ApplicationContextHolder;
import com.dhslrl321.controller.ByeController;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServletContainer implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext context) throws ServletException {

        AnnotationConfigApplicationContext applicationContext = ApplicationContextHolder.annotationConfigApplicationContext();

        context.addServlet("hello", new HelloServlet())
                .addMapping("/hello");

        context.addServlet("bye", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                ByeController controller = applicationContext.getBean(ByeController.class);
                resp.getWriter().println(controller.bye("jang"));
            }
        }).addMapping("/bye");
    }
}
