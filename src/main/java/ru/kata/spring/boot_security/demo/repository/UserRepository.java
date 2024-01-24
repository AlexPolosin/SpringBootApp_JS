package ru.kata.spring.boot_security.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserRepository {

    void addUser(User user);
    List<User> getListUsers();

    void deleteUser(Long id);

    void updateUser(User user);
    User getUser(Long id);

}
