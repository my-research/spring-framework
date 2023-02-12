package com.github.dhslrl321;

import com.github.dhslrl321.events.OrderedEvent;
import com.github.dhslrl321.infra.OrderListener;
import com.github.dhslrl321.infra.SpringAppEventPublisher;
import com.github.dhslrl321.infra.UserListener;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * @RecordApplicationEvents is a class-level annotation
 *  that is used to instruct the Spring TestContext Framework
 *  to record all application events
 *  that are published in the ApplicationContext during the execution of a single test.
 */
@SpringBootTest
@RecordApplicationEvents
class AppEventListenerPubSubTest {

    @Autowired
    SpringAppEventPublisher sut;
    @Autowired
    ApplicationEvents events;

    @MockBean
    private OrderListener orderListener;
    @MockBean
    private UserListener userListener;

    @Test
    @DisplayName("리스너는 본인이 수신하고자 하는 이벤트만 수신한다")
    void tc1() {
        OrderedEvent event = OrderedEvent.of("any", "any");

        sut.publish(event);

        verify(orderListener, times(1)).listen(event);
        verifyNoInteractions(userListener);
    }

    @Test
    @DisplayName("이벤트를 발행하면 이벤트 스트림에 이벤트가 추가된다")
    void tc2() {
        sut.publish(OrderedEvent.of("any", "any"));

        long count = events.stream(OrderedEvent.class).count();
        assertThat(count).isOne();
    }
}