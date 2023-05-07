# jdbc

- java database connectivity
  - java 가 제공하는 DB 연결 표준 interface
- 다음과 같은 핵심 컴포넌트
  - Connection : db 연결과 상호작용을 담당
  - Statement : db 에 실행될 쿼리 객체
  - ResultSet : 쿼리 결과에 따른 응답 객체


The typical workflow of using JDBC involves

- loading and registering the driver, 
- establishing a connection to the database
- creating 
- executing SQL statements, processing the results, 
- closing the resources when finished.

# Connection

- DB 접근 객체, 상호작용을 담당
- Connection 객체를 만드는 과정
  - DB 와 TCP/IP 연결을 시도 (3-way-handshake 와 같은 네트워크 행위)
  - DB 에게 세부 정보 (ID, PW) 를 전달
  - DB 는 해당 정보를 토대로 인증/인가를 수행하고 세션 생성
  - 세선 정보를 반환하고 해당 세션으로 Connection 객체를 생성
- 획득 방법
  - DriverManager
  - HikariPool

# DataSource: connection 을 획득하는 방법을 추상화



# connection pool

- Connection 객체를 만드는데 많은 비용이 듦
  - DB 와 TCP/IP 연결을 시도 (3-way-handshake 와 같은 네트워크 행위)
  - DB 에게 세부 정보 (ID, PW) 를 전달
  - DB 는 해당 정보를 토대로 인증/인가를 수행하고 세션 생성
  - 세선 정보를 반환하고 해당 세션으로 Connection 객체를 생성
- 계속해서 Connection 을 만들고 삭제하는 일이 비효율적이게 됨
- connection pool 은 Connection 을 어딘가에 미리 만들어놓고 사용하자는 idea 에서 출발
  - 제한된 수의 Connection 을 만들어서 재사용 가능한 풀로 관리
  - 언제든 DB 쿼리를 사용하고 커넥션을 반납하여 재사용 (close 하면 안됨)

### HikariConnectionPool

- Spring 진영에서 업계 표준으로 상요되고있는 connection pool
- 