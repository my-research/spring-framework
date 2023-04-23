# ProxyFactory

- spring
- interface 가 있다면 jdk dynamic proxy 를 사용, 없으면 cglib 를 사용
- proxyFactory 에게 프록시 생성을 요청

# Advice

- 프록시에 적용할 부가 기능 로직
- Advice 만 알고 구현하면 됨
- Advice 가 알아서 MethodInterceptor 나 InvocationHandler 를 호출
- MethodInterceptor 를 구현함
  - 얘는 cglib 의 methodInterceptor 가 아님, spring 이 제공하는 인터페이스이고 해당 인터페이스가 Advice 인터페이스를 확장하고 있음
