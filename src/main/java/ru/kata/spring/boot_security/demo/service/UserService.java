package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);

    List<User> getList();

    void updateUser(Long id, User user);

    void createUser(User user);

    void deleteUser(Long id);

    void deleteUser(User user);

    List<User> findUser(User user);
}
