package com.github.dhslrl321.events;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderedEvent implements CustomEvent {
    String orderId;
    String userId;
}
