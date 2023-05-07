# 개념

- Transaction 이란
  - 논리적인 하나의 작업으로 성공적으로 연산을 마치쳐 영속화 하거나(commit) 실패로 인해 초기 상태로 되돌아가는 것(rollback) 을 보장한다
  - ACID 라는 4가지 특성을 지원해야한다
    1. 원자성 (Atomicity)
    2. 일관성 (Consistency)
    3. 독립성 (Isolation)
    4. 지속성 (Durability)
  - 위의 4가지 특성 중에서 가장 중요한 것은 **독립성**임
    - 독립성을 strict 하게 지키기 위해서는 모든 트랜잭션을 줄세워서 하나씩 실행해야 하는데, 그게 쉽지 않음
    - 그리고 그렇게 한다면(blocking) 성능이 너무 안좋아짐 -> 병렬 처리가 안됨
    - 그래서 이런 특성을 조금 완화시키기 위한 방법이 바로 **고립 수준, Isolation Level**이라는 것임

# Transaction Isolation Level, 고립 수준

- 4가지 레벨이 존재
  - Read Uncommitted
  - Read Committed
  - Repeatable Read
  - Serializable
- 단계가 높아질수록 성능이 느려지지만, 확실한 일관성, 정합성이 보장됨
- 일반적으로는 Read Committed 수준으로 사용함

### 목차

- [auto commit 모드란](#)
  - [manual commit 방법 1. set autocommit false](#)
  - [manual commit 방법 2. begin transaction](#)
- [lock](#)
  - [update lock](#)
  - [select lock](#)