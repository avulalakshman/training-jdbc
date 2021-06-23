package com.heraizen.todo.repo;

import com.heraizen.todo.domain.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    
}
