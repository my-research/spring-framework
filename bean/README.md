# Bean

# Bean Post Processor

빈을 조작하고 변경할 수 있는 hooking 포인트

- 개발자가 등록하는 모든 bean 을 중간에 조작 (proxy) 할 수 있다는 뜻

## 동작 과정

1. 생성 : 빈 객체를 개발자가 생성함 (@Bean, @Component)
2. 전달 : 생성된 객체를 bean 저장소 (`SingletonBeanRegistry.java`) 에 등록하기 전에 빈 후처리기로 전달
3. 후 처리 작업 : 전달된 스프링 빈 객체를 조작
4. 등록 : 빈을 반환하고 빈 저장소에 저장

## BeanPostProcessor

- 해당 처리기를 사용하기 위해서는 `BeanPostProcessor` 를 구현하고 빈으로 등록하면 됨

```java
public interface BeanPostProcessor {

  // 초기화가 발생하기 전
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;


  // 초기화가 발생하고 나서
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
```
