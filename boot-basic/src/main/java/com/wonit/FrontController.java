package com.wonit;

import com.wonit.controller.ByeController;
import com.wonit.controller.HelloController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equals("/hello")) {
            HelloController controller = new HelloController();
            String queryString = request.getQueryString();
            if (queryString.equals("name")) {
                controller.hello(queryString);
            }
        } else if (request.getRequestURI().equals("/bye")) {
            ByeController controller = new ByeController();
            String queryString = request.getQueryString();
            if (queryString.equals("name")) {
                controller.bye(queryString);
            }
        } else {
            response.setStatus(404);
            response.getWriter().println("404 not found");
        }
    }
}
