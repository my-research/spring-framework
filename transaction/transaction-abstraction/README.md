# Transaction Abstraction

- JdbcTransaction 관리 방법과 Jpa 의 Transaction 관리 방법이 다름
- 처음에 JdbcTxManager 를 이용해서 코딩을 하다가 JpaTxManager 로 변경하려고 한다면 관련 코드를 모두 수정해야하기에 Tx 관리 방법을 추상화하여 사용하는 방법을 고안
- Spring 에서 Transaction 을 관리하기 위해서는 `PlatformTransactionManager` 라는 추상화를 제공함

# PlatformTransactionManager

- 주의: this is not primarily meant as an api, TransactionTemplate 을 사용할 것
- 제공하는 3가지 핵심 메서드
  - `getTransaction()`
  - `commit()`
  - `rollback()`
- `getTransaction()` 는 트랜잭션 전파기능이 존재하기 때문에 이미 열려있는 tx 를 꺼낼 수 있다.

# TransactionSynchronizationManager

- spring 이 관리하는 트랜잭션의 동기화 매니저
- 트랜잭션을 동기화해야하는 이유
  - 서로 다른 클래스 혹은 메서드를 호출하는 하나의 서비스에서 하나의 세션 트랜잭션을 이용해야하기 때문에 트랜잭션(세션)하나를 공유해야함
  - 그렇지 않으면 매번 새로운 세션이 열려야함 -> 그럼 트랜잭션 적용이 안됨
