package com.github.dhslrl321.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloController {
    public String hello(String name) throws IOException {
        return name + ", hello ! welcome to spring";
    }
}
