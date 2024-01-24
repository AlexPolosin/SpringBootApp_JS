package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    List<User> getListUsers();

    void deleteUser(Long id);

    void updateUser(User user);
    User getUser(Long id);
    User findByUsername(String username);

}
