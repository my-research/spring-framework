package com.dhslrl321;

import com.dhslrl321.servlet.MyServletContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class MainApp {

    static AnnotationConfigApplicationContext applicationContext = ApplicationContextHolder.annotationConfigApplicationContext();

    public static void main(String[] args) {

        applicationContext.scan("com.dhslrl321");
        applicationContext.refresh();

        WebServer webServer = new TomcatServletWebServerFactory().getWebServer(new MyServletContainer());
        webServer.start();
    }
}
