package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final UserServiceImpl userServiceimpl;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder, UserServiceImpl userServiceimpl) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userServiceimpl = userServiceimpl;
    }


    // Create
    @PostMapping("")
    public String createUser(@ModelAttribute("user") User user) { //todo
       userService.createUser(user);
        return "redirect:/admin";
    }

    //Update
    @PostMapping("/{id}")
    public String edit(Model model, @ModelAttribute("user") User user, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roleList", userService.getList());
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    // Delete
    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @GetMapping()
    public String show(Model model, User admin) {
        model.addAttribute("admin", admin);
        model.addAttribute("users", userService.getList());
        model.addAttribute("userRoles", roleService.getAllRoles());
        model.addAttribute("userNew", new User());
        model.addAttribute("rolesNew", roleService.getAllRoles());
        return "admin";
    }
}
