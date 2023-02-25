package com.github.dhslrl321.infra;

import com.github.dhslrl321.events.EventsListener;
import com.github.dhslrl321.events.OrderedEvent;
import com.github.dhslrl321.persist.entity.OrderEntity;
import com.github.dhslrl321.persist.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderListener implements EventsListener<OrderedEvent> {

    private final OrderRepository repository;

    @Override
    @EventListener
    public void listen(OrderedEvent event) {
        log.info("----orderListener------------{}", event.toString());
        repository.save(new OrderEntity(ZonedDateTime.now().toEpochSecond(), event.getUserId()));
        throw new RuntimeException();
    }
}
