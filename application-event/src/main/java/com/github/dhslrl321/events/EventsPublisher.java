package com.github.dhslrl321.events;

public interface EventsPublisher<T> {
    void publish(T t);
}
