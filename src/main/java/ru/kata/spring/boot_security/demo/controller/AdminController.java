package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping( "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    // Create

    @GetMapping("/new")
    public String createForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roleList", roles);
        return "new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) { //todo
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.createUser(user);

        return "redirect:/admin";
    }

    // Read

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getList());
        return "admin";
    }

    @GetMapping("/user/{id}")
    public String readUser(Model model, @PathVariable(name = "id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "show";
    }

    // Update

    @GetMapping("user/{id}/edit")
    public String editUser(Model model, @PathVariable() long id) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roleList", userService.getList());
        return "edit";
    }

    @PostMapping("edit/{id}")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    // Delete

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
