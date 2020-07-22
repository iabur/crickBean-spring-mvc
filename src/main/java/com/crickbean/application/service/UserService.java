package com.crickbean.application.service;

import com.crickbean.application.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //implementing a method of userDetailsService
    //first check the user by username
    //return a new user with username, password and authorities.
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var userFromDb = userRepository.findByUsername(s).orElseThrow(() ->
                new UsernameNotFoundException("No user found with this userName"));
        var authorities = userFromDb.getRoles();
        return new User(userFromDb.getUsername(), userFromDb.getPassword(), authorities);
    }

    //update a previous user
    public void update(com.crickbean.application.model.User userObj) {
        userRepository.save(userObj);
    }

    //find a user by id
    public Optional<com.crickbean.application.model.User> getUserByUserId(Long id) {
        return userRepository.findById(id);
    }

    public com.crickbean.application.model.User getLoggedUser(Authentication authentication){
        return userRepository.findByUsername(authentication.getName()).get();
    }

}
