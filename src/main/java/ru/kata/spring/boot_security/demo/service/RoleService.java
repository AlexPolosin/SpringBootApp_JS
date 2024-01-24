package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

}
