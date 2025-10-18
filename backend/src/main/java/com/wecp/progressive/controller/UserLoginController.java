package com.wecp.progressive.controller;

import com.wecp.progressive.dto.LoginRequest;
import com.wecp.progressive.dto.LoginResponse;
import com.wecp.progressive.entity.User;
import com.wecp.progressive.jwt.JwtUtil;
import com.wecp.progressive.repository.UserRepository;
import com.wecp.progressive.service.impl.UserLoginServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController


public class UserLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginServiceImpl userLoginServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername()) != null)
        {
            throw new RuntimeException("User already registerd");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) throws Exception {
        
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch(AuthenticationException aex)
        {
            throw new ResponseStatusException((HttpStatus.UNAUTHORIZED),"Inavlid Username or Password.",aex);

        }
        final UserDetails userDetails = userLoginServiceImpl.loadUserByUsername(loginRequest.getUsername());

        final String token = jwtUtil.generateToken(userDetails.getUsername());

        final User userObj = userRepository.findByUsername(loginRequest.getUsername());
        if(userObj == null )
        {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(new LoginResponse(token,userObj.getRole(),userObj.getUserId()));
    }
}
