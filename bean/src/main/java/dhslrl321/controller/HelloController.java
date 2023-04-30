package dhslrl321.controller;

import dhslrl321.service.HelloService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class HelloController {

    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    public String hello(String name) throws IOException {
        log.info(service.getHello());
        return name + ", hello ! welcome to spring";
    }
}
