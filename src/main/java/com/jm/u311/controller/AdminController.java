package com.jm.u311.controller;

import com.jm.u311.model.User;
import com.jm.u311.service.RoleService;
import com.jm.u311.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping
    public String admin(@ModelAttribute("newUser") User newUser, Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }
    @PostMapping("/add")
    public String create(User user, @RequestParam(value = "rolesAdd", defaultValue = "USER") String... roles){
        user.setRoles(Arrays.stream(roles).map(roleService::getRoleByName).collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/update/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "update";
    }
    @PatchMapping("/update")
    public String update(User user, @RequestParam(value = "rolesUpdate", defaultValue = "USER") String... roles){
        user.setRoles(Arrays.stream(roles).map(roleService::getRoleByName).collect(Collectors.toSet()));
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
