package com.github.dhslrl321;

import com.github.dhslrl321.servlet.MyServletContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

@Slf4j
public class MainApp {
    public static void main(String[] args) {
        MyServletContainer servletContainer = new MyServletContainer();

        WebServer webServer = new TomcatServletWebServerFactory().getWebServer(servletContainer);
        webServer.start();
        log.info("[JWI] server started !! on {}", webServer.getPort());
    }
}
