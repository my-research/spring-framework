package com.dhslrl321;

import com.dhslrl321.servlet.MyServletContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class MainApp {

    public static void main(String[] args) {

        GenericApplicationContext applicationContext = new GenericApplicationContext() {
            @Override
            protected void onRefresh() throws BeansException {
                WebServer webServer = new TomcatServletWebServerFactory().getWebServer(new MyServletContainer());
                webServer.start();
            }
        };

        applicationContext.refresh();

    }
}
