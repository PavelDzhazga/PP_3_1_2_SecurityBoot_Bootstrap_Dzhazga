package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Long id) {
        return userRepository.getUser(id);
    }

    @Override
    public List<User> getList() {
        return userRepository.getList();
    }

    @Override
    public void updateUser(Long id, User user) {
        userRepository.updateUser(id, user);
    }

    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteUser(id);

    }

    @Override
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    @Override
    public List<User> findUser(User user) {
        return userRepository.findUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username);
    }
}
