package com.heraizen.todo.controller;

import java.util.List;

import com.heraizen.todo.domain.Todo;
import com.heraizen.todo.service.TotodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/todo/")
public class TotoController {

        @Autowired
        private TotodService totodService;

        @PostMapping("{userid}")
        public ResponseEntity<Todo> addTodo(@PathVariable("userid") Long userId,@RequestBody Todo todo){
               todo=totodService.addTodo(todo, userId);
               return ResponseEntity.ok().body(todo);
        }
        @GetMapping("{userid}")
        public ResponseEntity<List<Todo>> getTodos(@PathVariable("userid") Long userId){
                List<Todo> list = totodService.getTodos(userId);
                return ResponseEntity.ok().body(list);
        }

        @DeleteMapping("{todoid}")
        public ResponseEntity<ResponseMessage> addTodo(@PathVariable("todoid") Long todoId){
               boolean isDeleted=totodService.removeTodo(todoId);
               String message = isDeleted ? todoId+" is deleted":todoId +" not found / not deleted";
               return ResponseEntity.ok().body(new ResponseMessage(message));
        }

}
