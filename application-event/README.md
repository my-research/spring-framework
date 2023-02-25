# application event

- 기본적인 이벤트 사용법
- transactional 의 범위, sync/async
- test code

## transaction rollback 영향

## sync, async

- 일반적으로 사용하게되면 synchronous 하지만 Listener 에 `@Async` 어노테이션을 class level 에 명시하면 asynchronous 하게 동작한다
  - 다른 thread 에서 listener 가 동작한다
  - `@EnableAync` 를 통해서 spring application 을 asynchronous 실행 가능하도록 해줘야 한다