package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


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

    @GetMapping("")
    public String getListUsers(Model model, Principal principal) {
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

    @GetMapping("/create")
    public String addUser(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("newUser") User user) {

        userService.saveUser(user);

        return "redirect:/admin";
    }
    @GetMapping("/delete/edit/id")
    public String deleteUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "delete";
    }

    @PostMapping("/delete/id")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/edit/id")
    public String updateUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "update";
    }

    @PostMapping("/update/id")
    public String updateUser(@RequestParam Long id, @ModelAttribute("user") User user) {
        User updateUser = userService.findUserById(id);
        updateUser.setName(user.getName());
        updateUser.setPassword(user.getPassword());
        updateUser.setRoles(user.getRoles());
        updateUser.setEmail(user.getEmail());
        userService.saveUser(updateUser);
        return "redirect:/admin";
    }
}
