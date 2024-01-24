package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.entity.User;



@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getListUsers(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/create")
    public String addUser(User user, Model model) {
        model.addAttribute("roles", roleService.getRoles());
        return "create";
    }

    @PostMapping("/create")
    public String saveUser(User user) {
        userService.addUser(user);

        return "redirect:/admin";
    }
    @GetMapping("/delete/edit/id")
    public String deleteUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getRoles());
        return "delete";
    }

    @PostMapping("/delete/id")
    public String deleteUser(@RequestParam Long id, @ModelAttribute("user") User user, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update/edit/id")
    public String updateUserForm(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getRoles());
        return "update";
    }

    @PostMapping("/update/id")
    public String updateUser(@RequestParam Long id, @ModelAttribute("user") User user, Model model) {
        User updateUser = userService.getUser(id);
        updateUser.setName(user.getName());
        updateUser.setPassword(user.getPassword());
        updateUser.setRoles(user.getRoles());
        updateUser.setEmail(user.getEmail());
        userService.updateUser(updateUser);
        return "redirect:/admin";
    }
}
