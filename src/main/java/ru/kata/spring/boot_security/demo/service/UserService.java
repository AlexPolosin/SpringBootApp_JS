package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    User findUserById(Long id);
    List<User> getUsers();
    boolean saveUser(User user);
    boolean deleteUser(Long id);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
