package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class Init implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args)  {
        if (roleService.getAllRoles().isEmpty()) {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            roleService.addRole(roleAdmin);
            roleService.addRole(roleUser);

            Set<Role> setAdmin = new HashSet<>();
            Set<Role> setUser = new HashSet<>();

            setAdmin.add(roleAdmin);
            setAdmin.add(roleUser);
            setUser.add(roleUser);

            User admin = new User("Rex","lastname","rex@mail.ru", "password",setAdmin);
            User user = new User("Ivan", "Ivanov", "ivanov@mail.ru", "password",setUser);
            userService.createUser(admin);
            userService.createUser(user);



        }
    }
}
