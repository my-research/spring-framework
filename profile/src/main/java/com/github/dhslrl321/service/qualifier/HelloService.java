package com.github.dhslrl321.service.qualifier;

public interface HelloService<T> {
    String hello(T t);
}
