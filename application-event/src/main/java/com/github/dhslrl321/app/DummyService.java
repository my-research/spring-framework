package com.github.dhslrl321.app;

import com.github.dhslrl321.events.OrderedEvent;
import com.github.dhslrl321.events.SignedUpEvent;
import com.github.dhslrl321.infra.SpringAppEventPublisher;
import com.github.dhslrl321.persist.entity.UserEntity;
import com.github.dhslrl321.persist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DummyService {

    private final SpringAppEventPublisher publisher;
    private final UserRepository repository;

    @Transactional
    public void order() {
        publisher.publish(OrderedEvent.of("123", "999"));
        UserEntity jang = new UserEntity(1L, "jang");
        repository.save(jang);
    }

    public void dummy2() {
        publisher.publish(SignedUpEvent.of("1"));
    }
}
