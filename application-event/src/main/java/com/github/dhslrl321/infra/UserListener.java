package com.github.dhslrl321.infra;

import com.github.dhslrl321.events.EventsListener;
import com.github.dhslrl321.events.SignedUpEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserListener implements EventsListener<SignedUpEvent> {
    @Override
    @EventListener
    public void listen(SignedUpEvent event) {
        log.info("----------------{}", event.toString());
    }
}
