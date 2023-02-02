package com.github.dhslrl321.infra;

import com.github.dhslrl321.events.CustomEvent;
import com.github.dhslrl321.events.EventsPublisher;
import com.github.dhslrl321.events.OrderedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringAppEventPublisher implements EventsPublisher<CustomEvent> {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(CustomEvent customEvent) {
        publisher.publishEvent(customEvent);
    }
}
