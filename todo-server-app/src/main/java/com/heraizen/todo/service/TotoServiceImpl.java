package com.heraizen.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.heraizen.todo.domain.AppUser;
import com.heraizen.todo.domain.Todo;
import com.heraizen.todo.repo.AppUserRepo;
import com.heraizen.todo.repo.TodoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TotoServiceImpl implements TotodService{
    
        private final AppUserRepo appUserRepo;
        private final TodoRepo todoRepo;
        
        @Autowired
        public TotoServiceImpl(AppUserRepo appUserRepo, TodoRepo todoRepo) {
            this.appUserRepo = appUserRepo;
            this.todoRepo = todoRepo;
        }

        @Override
        public Todo addTodo(Todo todo, Long userId) {
            Optional<AppUser> optUser = appUserRepo.findById(userId);
            optUser.ifPresentOrElse(u->{
                           todo.setAppUser(u);
                    Todo savedTodo=todoRepo.save(todo); 
                    log.info("Todo is added with id :{} for user :{}",savedTodo.getId(),u.getUsername());
                    todo.setId(savedTodo.getId());

            },()->{
                   log.error("User with id :{} is not found", userId);
                   throw new IllegalArgumentException("Invalid user id");
            });
            return todo;
        }

        @Override
        public List<Todo> getTodos(Long userId) {
            Optional<AppUser> optUser = appUserRepo.findById(userId);
            List<Todo> list = new ArrayList<>();
            optUser.ifPresentOrElse(u->{
                         list.addAll(u.getTodos());
                         log.info("User :{} has total {} todos",u.getUsername(),list.size());
            },()->new IllegalArgumentException("Invalid user id"));
            return list;
        }

        @Override
        public boolean removeTodo(Long todoId) {
               Optional<Todo> optTodo = todoRepo.findById(todoId);
               optTodo.ifPresentOrElse(t->{     
                 todoRepo.delete(t);
                 log.info("Todo with id :{} is delted",todoId);
               }, ()->new IllegalArgumentException("Todo with id :"+todoId+" is not found"));
               return true;
            }

}
