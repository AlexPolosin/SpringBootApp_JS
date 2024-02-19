package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Collection;


@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Collection<Role> roles = user.getRoles();
        model.addAttribute("user", user);
        for(Role role : roles) {
            model.addAttribute("role", role);
        }
        return "user";
    }

    @GetMapping("/api")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
