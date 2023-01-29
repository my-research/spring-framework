# Spring Transaction Propagation

- 하나의 트랜잭션에서 새로운 트랜잭션을 만나면 어떻게 해야하는가? 에 대한 이야기입니다

# Concept

- `PROPAGATION_REQUIRED`
    - 가장 많이 사용되는 트랜잭션 선파 속성
    - 진행중인 tx 가
        - 없으면 새로 시작
        - 있으면 합류
- `PROPAGATION_REQUIRES_NEW`
    - 항상 새로운 tx 시작
    - 독립적인 tx 가 보장되어야할 때 사용
- `PROPAGATION_NOT_SUPPORTED`
    - tx 없이 동작하도록 함

# Practice

- 테스트
  - **arrange**
    - accessHistory 테이블을 생성한다
    - user 테이블을 생성한다.
    - `localhost:8080/hello` 를 호출하면 다음과 같은 일이 발생한다
      1. accessHistory 테이블에 로우 추가
      2. user 생성 및 저장
  - **act**
    - `localhost:8080/hello` 호출시 user 생성할 때 예외를 발생시킨다
  - **actual**
    - accessHistory 의 propagation 이 requires_new 이기에 user 는 저장되지 않지만 accessHistory 는 저장된다

### 연관된 키워드 및 더 알아봐야 할 것

- TransactionManager
- TransactionAdvice