package dhslrl321.config;

import dhslrl321.controller.HelloController;
import dhslrl321.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public HelloService helloService() {
        return new HelloService();
    }

    @Bean
    public HelloController helloController(HelloService service) {
        return new HelloController(service);
    }
}
