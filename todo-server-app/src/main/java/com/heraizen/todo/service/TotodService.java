package com.heraizen.todo.service;

import java.util.List;

import com.heraizen.todo.domain.Todo;

public interface TotodService {
        Todo addTodo(Todo todo,Long userId);
        List<Todo> getTodos(Long userId);
        boolean removeTodo(Long totoId);
}
