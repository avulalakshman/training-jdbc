package com.heraizen.todo.repo;

import java.util.List;

import com.heraizen.todo.domain.Status;
import com.heraizen.todo.domain.Todo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepo extends JpaRepository<Todo,Long> {
    
        public List<Todo> findByStatus(Status status);

    
        
}
