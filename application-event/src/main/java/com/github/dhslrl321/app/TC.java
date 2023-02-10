package com.github.dhslrl321.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
public class TC {

    boolean isNew;

    @JsonProperty("isNew")
    public boolean isNew() {
        return isNew;
    }
}
