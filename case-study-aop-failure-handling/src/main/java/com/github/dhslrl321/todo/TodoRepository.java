package com.github.dhslrl321.todo;

import com.github.dhslrl321.todo.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
