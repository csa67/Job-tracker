package com.app.job_tracker.controller;

import com.app.job_tracker.entity.User;
import com.app.job_tracker.repository.UserRepo;
import com.app.job_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public User getUserDetails(){
        return userService.getUserDetails();
    }
}
