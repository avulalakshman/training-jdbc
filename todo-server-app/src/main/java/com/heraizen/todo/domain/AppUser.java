package com.heraizen.todo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AppUser {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private String username;
        @Column(unique = true)
        private String email;
        
        @OneToMany(mappedBy = "appUser")
        private List<Todo> todos = new ArrayList<>();

        public void addTodo(Todo todo){
                this.todos.add(todo);
                todo.setAppUser(this);
        }
        
        public void removeTodo(Todo todo){
            this.todos.remove(todo);
            todo.setAppUser(null);
    }
}
