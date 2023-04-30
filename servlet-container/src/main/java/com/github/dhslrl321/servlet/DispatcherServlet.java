package com.github.dhslrl321.servlet;

import com.github.dhslrl321.controller.ByeController;
import com.github.dhslrl321.controller.HelloController;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    HelloController helloController = new HelloController();
    ByeController byeController = new ByeController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getQueryString().split("=")[1];
        if (req.getRequestURI().equals("/hello")) {
            resp.getWriter().println(helloController.hello(name));
        } else if (req.getRequestURI().equals("/bye")) {
            resp.getWriter().println(byeController.bye(name));
        }
        resp.setStatus(HttpStatus.OK.value());
    }
}
