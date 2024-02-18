package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.Collection;

@Controller
public class IndexController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public IndexController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
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
    @GetMapping(value = "/user")
    public String getUserPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Collection<Role> roles = user.getRoles();
        model.addAttribute("user", user);
        for(Role role : roles) {
            model.addAttribute("role", role);
        }
        return "user";
    }




}
