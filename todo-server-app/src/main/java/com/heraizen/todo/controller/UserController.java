package com.heraizen.todo.controller;

import com.heraizen.todo.domain.AppUser;
import com.heraizen.todo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping
        public ResponseEntity<AppUser> registerUser(@RequestBody AppUser appUser){
               appUser = userService.addUser(appUser);
               return ResponseEntity.ok().body(appUser);
        }
}
