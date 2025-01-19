package com.app.job_tracker.service;

import com.app.job_tracker.entity.User;
import com.app.job_tracker.model.LoginCreds;
import com.app.job_tracker.repository.UserRepo;
import com.app.job_tracker.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, Object> registerUser(User user ){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepo.save(user);

        return createJWTforUser(user.getUsername());
    }

    public Map<String,Object> loginUser(@RequestBody LoginCreds body) throws AuthenticationException{

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        authenticationManager.authenticate(authInputToken);

        return createJWTforUser(body.getUsername());

    }

    public Map<String, Object> createJWTforUser(String username){
        String token = jwtUtil.generateToken(username);
        return Collections.singletonMap("jwt-token",token);
    }


}
