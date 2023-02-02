package com.github.dhslrl321.infra;

import com.github.dhslrl321.events.EventsListener;
import com.github.dhslrl321.events.OrderedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener implements EventsListener<OrderedEvent> {
    @Override
    @EventListener
    public void listen(OrderedEvent event) {
        log.info("----------------{}", event.toString());
    }
}
