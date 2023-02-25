# TransactionInterceptor

- extends
  - TransactionAspectSupport.java

```
public class TransactionInterceptor extends TransactionAspectSupport implements MethodInterceptor, Serializable {

}
```

- spring tx infra 에서 사용되는 AOP Alliance MethodInterceptor for 선언적 tx 관리 도구
- proxy 를 이용해서 실행할 코드를 받아옴
