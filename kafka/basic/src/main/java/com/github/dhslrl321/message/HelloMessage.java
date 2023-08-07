package com.github.dhslrl321.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class HelloMessage {
    String value;
}
