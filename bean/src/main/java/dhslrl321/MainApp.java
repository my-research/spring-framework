package dhslrl321;

import dhslrl321.controller.ByeController;
import dhslrl321.controller.HelloController;
import dhslrl321.service.HelloService;
import dhslrl321.servlet.MyServletContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Controller;

@Slf4j
public class MainApp {

    static GenericApplicationContext applicationContext = new GenericApplicationContext();

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void main(String[] args) {

        applicationContext.registerBean("hc", HelloController.class, new HelloService());
        applicationContext.registerBean("bc", ByeController.class);

        /**
         * Typical usage is to register a variety of bean definitions via the BeanDefinitionRegistry interface
         * and then call refresh() to initialize those beans with application context semantics
         */
        applicationContext.refresh();

        MyServletContainer servletContainer = new MyServletContainer();

        WebServer webServer = new TomcatServletWebServerFactory().getWebServer(servletContainer);
        webServer.start();
        log.info("[JWI] server started !! on {}", webServer.getPort());
    }
}
