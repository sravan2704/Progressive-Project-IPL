package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.User;
import com.wecp.progressive.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserLoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> userOp = userRepository.findById(user.getUserId());
        User userObj = userOp.get();
        userObj.setUserId(user.getUserId());
        userObj.setFullName(user.getFullName());
        userObj.setEmail(user.getEmail());
        userObj.setPassword(user.getPassword());
        userObj.setRole(user.getRole());
        userObj.setUsername(user.getUsername());
        return userRepository.save(userObj);
    }

    public void deleteUser(Integer id) {

        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        
        User user = userRepository.findByUsername(username);
        if(user == null)
        {
            throw new RuntimeException("user not found");
        }

        UserDetails userDetailsObj = new org.springframework.security.core.userdetails.User(
            user.getUsername(),user.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRole()))

        );
        return userDetailsObj;

    }
}