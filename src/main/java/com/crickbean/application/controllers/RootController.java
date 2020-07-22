package com.crickbean.application.controllers;

import com.crickbean.application.model.Role;
import com.crickbean.application.model.User;
import com.crickbean.application.repositories.AuthorityRepository;
import com.crickbean.application.repositories.UserRepository;
import com.crickbean.application.service.AuthorityService;
import com.crickbean.application.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.Authenticator;
import java.nio.channels.MulticastChannel;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RootController {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final UserService userService;

    public RootController(AuthorityRepository authorityRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityService authorityService, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityService = authorityService;
        this.authorityRepository = authorityRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String root(Authentication authentication, Model model) {
        model.addAttribute("user",userService.getLoggedUser(authentication));
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        generateRoles();
        generateUsers();
        model.addAttribute("error", error);
        return "auth/login";
    }

    private void generateRoles() {

        authorityService.create(new Role(System.nanoTime(), "ROLE_ADMIN"));
        authorityService.create(new Role(System.nanoTime(), "ROLE_COUNTRY_MANAGER"));
        authorityService.create(new Role(System.nanoTime(), "ROLE_PLAYER"));
        authorityService.create(new Role(System.nanoTime(), "ROLE_STUFF"));
        authorityService.create(new Role(System.nanoTime(), "ROLE_ICC_EMPLOYEE"));
        authorityService.create(new Role(System.nanoTime(), "ROLE_TEAM_MANAGER"));
    }

    private void generateUsers() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            var user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("secret"));
            Set<Role> roles = new HashSet<>();
            roles.add(authorityService.findByRoleName("ROLE_ADMIN"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        if (userRepository.findByUsername("icc").isEmpty()) {
            var user = new User();
            user.setUsername("icc");
            user.setPassword(passwordEncoder.encode("secret"));

            Set<Role> roles = new HashSet<>();
            roles.add(authorityService.findByRoleName("ROLE_ICC_EMPLOYEE"));
            user.setRoles(roles);

            userRepository.save(user);
        }
    }

    @GetMapping("/users")
    public String userList(Model model, Authentication authentication){
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("user",userService.getLoggedUser(authentication));
        return "admin/user/users";
    }

}
