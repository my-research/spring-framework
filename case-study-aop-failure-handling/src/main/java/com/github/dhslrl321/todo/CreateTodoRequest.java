package com.github.dhslrl321.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.Value;

@Data
@ToString
@AllArgsConstructor(staticName = "of")
public class CreateTodoRequest {
    private Long userId;
    private String name;
}
