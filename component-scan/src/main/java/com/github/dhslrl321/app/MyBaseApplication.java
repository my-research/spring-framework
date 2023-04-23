package com.github.dhslrl321.app;

import com.github.dhslrl321.dog.DogBeanScanHere;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// scan pkg 를 변경하면 기본 값으로 지정된 pkg 의 scan 을 잃어버리니 명시해주어야 한다
@SpringBootApplication(scanBasePackageClasses = {MyBaseApplication.class, DogBeanScanHere.class})
public class MyBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBaseApplication.class, args);
        System.out.println();
    }
}

