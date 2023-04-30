# Servlet

- Servlet 란
- ServletContainer 란

# servlet 이란

- java based server side component
- http 요청을 동적으로 처리하는 서버 컴포넌트
    - 요청에 따라 적합한 응답을 다르게 내려줄 수 있다
- 클라이언트가 서버로 요청을 보내면 서버는 적절한 servlet 을 찾아서 해당 servlet 에게 처리하도록 함 

# servlet container 란 위의 servlet 을 모아놓은 컨테이너

- servlet 들을 관리하는 컨테이너
    - lifecycle
    - instantiation
    - threading
    - resource allocation

# apache tomcat

- JSA (java servlet api) 를 구현한 대표적 servlet container

# Spring 과 Servlet 의 관계

- spring mvc 에서 servlet 을 관리함 
- Spring ApplicationContext 가 대표적인 