# Proxy

- 대체 기능을 수행하는 객체를 앞단에 위치시키고 실제 타겟의 기능을 대신 수행한다
  - decorator 와 유사하다
- proxy 구현 방법
  - 구현 (interface) 기반
  - 상속 (extends) 기반
  - reflection 을 이용한 proxy
    - reflection 을 이용해서 소스코드를 meta 정보로 만들면 된다
    - proxy 클래스를 직접 만들어줘야함

### lib 를 이용한 Proxy

- **Jdk dynamic proxy**
  - 인터페이스가 필수임
  - 앞선 proxy 기본 구현은 객체가 100개라면 proxy class 도 100개여야 하는데 이러한 reflection 을 이용하면 메서드 메타정보를 이용하여 특정 로직만 실행할 수 있게 해준다
- **CGLIB**
  - Code Generator Library
  - 바이트코드를 조작하여 동적으로 클래스를 생성하는 기술을 제공하는 라이브러리
  - 인터페이스 없이 만들 수 있음

# Spring 이 지원하는 Proxy, ProxyFactory

- interface 유무에 따라서 자동으로 jdk dynamic proxy 나 cglib 를 결정
- proxyFactory 에게 프록시 생성을 요청

### ProxyFactory 의 Advice

- 프록시에 적용할 부가 기능 로직
- Advice 만 알고 구현하면 됨
- Advice 가 알아서 MethodInterceptor 나 InvocationHandler 를 호출
- MethodInterceptor 를 구현함
  - 얘는 cglib 의 methodInterceptor 가 아님, spring 이 제공하는 인터페이스이고 해당 인터페이스가 Advice 인터페이스를 확장하고 있음

# Spring 이 지원하는 빈 후처리기

```groovy
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

- aspectJ 라이브러리를 자동으로 bean 등록
- spring boot 가 AOP 관련 클래스를 자동으로 빈 등록
  - `@EnableAspectJAutoProxy` 를 대신 해줌

### AutoProxyCreator

- 자동으로 프록시를 생성해주는 BeanPostProcessor
  - 자동으로 등록된 Advisor 들을 찾아서 프록시가 필요한 곳에 자동으로 프록시를 적용해줌

# `@Aspect` 어노테이션

- spring 이 편하게 aop 를 만들어줌

# 프록시와 내부 호출 문제

- 내부 메서드 호출에서 문제가 발생함

```java
public class Main {
  public void doA() {
    log.info("hello");
    this.innerB(); // proxy 호출 안됨
  }

  public void innerB() {
    log.info("world");
  }
}
```

- 해결방법
  - setter injection
  - applicationContext get Bean
  - 구조 변경
