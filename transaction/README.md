# Transaction

- 두가지 문제 상황을 재현함
  - 내부 Transactional 메서드 호출하면 rollback 이 되지 않는 문제
  - 서로 다른 service 를 호출할 때 예외를 catch 해도 rollback 이 되는 상황 (RuntimeException)

# Transactional

- `@Transactional` 을 붙인 메서드에는 **TransactionInterceptor** 가 동작한다
  - `PlatformTransactionManager#invokeWithinTransaction()` 이 호출
  - invocation 에서 예외가 발생되면 `PlatformTransactionManager#completeTransactionAfterThrowing()` 이 호출
  - `transactionInfo#rollback()` 호출

# proxy 관련된 문제

```java
@Service
public class SomeService {

  private final FirstRepository firstRepository;
  private final SecondRepository secondRepository;

  void first() {
    second();
    firstRepository.save(new First());
    throw new RuntimeException();
  }

  @Transactional
  void second() {
    secondRepository.save(new Second());
  }
}

class Test {
  @Test
  void name4() {
    assertThatThrownBy(() -> sut.first());

    assertThat(firstEntityRepository.findAll().size()).isEqualTo(1); // save
    assertThat(secondEntityRepository.findAll().size()).isEqualTo(0); // rollback
  }
}
```

- @Transactional 어노테이션이 붙어있는 second 는 rollback 이 되어야 한다
- 하지만 실제로는 rollback 이 안된다

# rollback 의 동작 과정

- TransactionInterceptor 에 의해서 exception 이 발생하면 `completeTransactionAfterThrowing` 를 호출함
- TransactionManager 의 rollback 을 통해 rollback 진행

# 위의 문제

- first 메서드를 실행시키면 TransactionInterceptor 자체가 동작하지 않음

# 이유

- 메서드를 실행할 때, AOP 에 의해서 Proxy 가 생성됨
- 해당 메서드에는 TransactionInterceptor 에게 proxy 를 생성해야 한다는 것을 명시하지 않았음
- 결국 proxy 없이 메서드 플로우 진행
- 이후 메서드에서 @Tx 를 만나더라도 proxy 를 만들어야한다는 아무 의심 없이 플로우가 진행됨
- 결국 rollback 불가
