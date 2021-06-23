package com.heraizen.todo.service;

import javax.transaction.Transactional;

import com.heraizen.todo.domain.AppUser;
import com.heraizen.todo.repo.AppUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    
    @Autowired
    private AppUserRepo appUserRepo;
    
    @Override
    public AppUser addUser(AppUser user) {
        Assert.notNull(user,"User object can't be null");
        Assert.notNull(user.getEmail(), "User email can't be empty or null");
        Assert.notNull(user.getUsername(), "User name can't be empty or null");   
        user = appUserRepo.save(user);
        log.info("User registred with :{} and name {} ",user.getId(),user.getUsername());
        return user;
    }
    
}
