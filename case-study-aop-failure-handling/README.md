# failure logging

- business logic 처리에 실패한 경우 요청 전문을 db 에 저장합니다

# 요구사항

- 어떤 행위에서 발생했는지 확인할 수 있다.
- 어떤 예외 상황이 발생했는지 확인할 수 있다
- 어떤 매개변수에 의해 동작했는지 확인할 수 있다
- 클라이언트는 key 를 통해 매개변수를 검색할 수 있다
- 문제가 발생한 시점을 확인할 수 있다

```java
class FailureLog {
    private String id;
    private ActionType type;
    private String key;
    private String payload;
    private String errorMessage;
    private String exception;
    private LocalDateTime occurredAt;
}
```
