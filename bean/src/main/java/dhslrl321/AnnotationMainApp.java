package dhslrl321;

import dhslrl321.servlet.MyServletContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AnnotationMainApp {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan();
        applicationContext.refresh();

        MyServletContainer servletContainer = new MyServletContainer();

        WebServer webServer = new TomcatServletWebServerFactory().getWebServer(servletContainer);
        webServer.start();
        log.info("[JWI] server started !! on {}", webServer.getPort());
    }
}
