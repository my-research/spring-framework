package com.github.dhslrl321.aop.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 어드바이스와 pointcut 을 분리할 수 있음
 */
public class Pointcuts {
    @Pointcut("execution(* com.github.dhslrl321.aop..*(..))") // 모든 하위 패키지에 대해
    public void allOrder() {}

    @Pointcut("execution(* *..*Service.*(..))") // 모든 Service 에 대해
    public void allService() {}

    @Pointcut("allOrder() && allService()") //  // 1번 pointcut 과 2번 pointcut 이 만족할 때, (||), (!) 도 가능
    public void allOrderAndService() {}

    /**
     * 만약 pointcut 에 대해서 순서를 지정하고 싶다면?
     *
     * class 로 분리하고 @Order(n) 어노테이션으로 bean 등록 순서를 조절해주면 됨
     */
}
