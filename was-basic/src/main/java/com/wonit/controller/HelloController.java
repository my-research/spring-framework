package com.wonit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloController {
    public void hello(String name) throws IOException {
        System.out.println("name = " + name);
    }
}
