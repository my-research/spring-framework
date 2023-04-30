package dhslrl321.servlet;

import dhslrl321.MainApp;
import dhslrl321.controller.ByeController;
import dhslrl321.controller.HelloController;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext applicationContext = MainApp.getApplicationContext();

        String name = req.getQueryString().split("=")[1];
        if (req.getRequestURI().equals("/hello")) {
            HelloController hc = applicationContext.getBean("hc", HelloController.class);
            resp.getWriter().println(hc.hello(name));
        } else if (req.getRequestURI().equals("/bye")) {
            ByeController bc = applicationContext.getBean("bc", ByeController.class);
            resp.getWriter().println(bc.bye(name));
        }
        resp.setStatus(HttpStatus.OK.value());
    }
}
