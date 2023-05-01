package com.dhslrl321;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextHolder {

    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    public static AnnotationConfigApplicationContext annotationConfigApplicationContext() {
        return context;
    }
}
