package com.github.dhslrl321.app;

import com.github.dhslrl321.events.OrderedEvent;
import com.github.dhslrl321.infra.SpringAppEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DummyService {
    private final SpringAppEventPublisher publisher;

    public void order() {
        publisher.publish(OrderedEvent.of("123", "999"));
    }
}
