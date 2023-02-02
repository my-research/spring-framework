package com.github.dhslrl321.events;

import lombok.Value;

@Value(staticConstructor = "of")
public class SignedUpEvent implements CustomEvent {
    String userId;
}
