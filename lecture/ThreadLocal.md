# ThreadLocal

- 스레드만 접근할 수 있도록 하는 저장소
  - 각 스레드가 별도의 내부 저장소를 사용함
  - 값을 저장하고 조회하는것은 모두 시작 스레드에 할당된 공간에서 꺼내온다
  - java 에서는 언어 차원의 `java.lang.ThreadLocal` 이 존재함

```java
public class Main {
  public static void main(String[] args) {
    ThreadLocal<String> tLocal = new ThreadLocal();

    tLocal.set("A");
    String actual = tLocal.get();

    validate(actual.equals("A"), IllegalStateException.class);
    tLocal.remove();
  }
}
```

# 주의사항

- ThreadLocal 을 사용하고 나면 꼭 remove 해줘야 한다
  - threadPool 을 사용할 때는 Thread 를 재사용하므로 ThreadLocal 의 값을 다른 요청 혹은 연산에서 사용될 수 있다
