package com.heraizen.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.heraizen.todo.domain.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TodoReports {

        @Autowired
        private EntityManager em;
        public List<Todo> getTodosByUsers(Long userId){
                List<Todo> list =
                             em.createNativeQuery("select t.* from todo t where t.user_id=?1", Todo.class).setParameter(1, userId).getResultList();
                return list;
                  
        }
    
}
