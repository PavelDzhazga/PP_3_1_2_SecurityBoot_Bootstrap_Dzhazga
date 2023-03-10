package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository {

    void createUser(User user);

    User getUser(Long id);

    List<User> getList();

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void deleteUser(User user);

    List<User> findUser(User user);

    User getUserByUsername (String username);
}
