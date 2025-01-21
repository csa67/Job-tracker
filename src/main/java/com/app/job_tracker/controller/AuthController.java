package com.app.job_tracker.controller;

import com.app.job_tracker.entity.User;
import com.app.job_tracker.model.LoginCreds;
import com.app.job_tracker.model.UserDto;
import com.app.job_tracker.repository.UserRepo;
import com.app.job_tracker.security.JWTUtil;
import com.app.job_tracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerHandler(@Valid @RequestBody UserDto userdto) {
        return ResponseEntity.ok(authService.registerUser(userdto));
    }

    @PostMapping("/login")
    public Map<String,Object> loginHandler(@RequestBody LoginCreds body){
        try{
            return authService.loginUser(body);
        } catch(AuthenticationException authExc){
            throw new RuntimeException("Invalid username/password.");
        }

    }

}
