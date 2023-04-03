# Component Scan

- Spring Bean 으로 클래스를 등록하기 위해서 spring 에게 bean 으로 등록할 클래스들이 어디에 있는지 알려주는 방법

# `@ComponentScan`

- `@Configuration` 어노테이션과 함께 `@ComponentScan` 을 사용하여 특정 패키지를 스캔할지 스프링에게 알려준다
- scanning 할 대상을 지정할 수 있음
  - `basePackages`
    - 명시된 패키지에 컴포넌트를 스캔
  - `basePackageClasses`
    - basePackages 의 type-safe 한 버전
    - `BeanScanHere.java` 와 같은 형태로도 사용됨
    - 그럼 해당 자바 파일이 존재하는 패키지를 bean scanning

## `@SpringBootApplication` 어노테이션을 사용하면 대신 해준다

다음 3가지의 annotation 이 `@SpringBootApplication` 어노테이션 안에 존재함

```java
@Configuration
@EnableAutoConfiguration
@ComponentScan
```

# component scanning 과정

<img width="662" alt="image" src="https://user-images.githubusercontent.com/48385288/229546354-a1a0da64-1674-44c1-8c3c-84a788b7fca3.png">
