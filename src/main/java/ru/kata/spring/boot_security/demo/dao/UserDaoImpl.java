package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) { entityManager.persist(user); }

    @Override
    public List<User> getListUsers() {

        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUser(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
