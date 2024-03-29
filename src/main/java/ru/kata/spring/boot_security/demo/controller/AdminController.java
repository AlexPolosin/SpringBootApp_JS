package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        User user = userService.findByUsername(principal.getName());
        Collection<Role> roles = user.getRoles();
        for(Role role : roles) {
            model.addAttribute("role", role);
        }
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin";
    }

    @GetMapping("/api")
    public ResponseEntity<List<User>> getListUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/api")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updateUser = userService.findUserById(id);
        updateUser.setUsername(user.getUsername());
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setRoles(user.getRoles());
        userService.saveUser(updateUser);
        return ResponseEntity.ok(updateUser);
    }
}
