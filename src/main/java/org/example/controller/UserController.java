package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listAll());
        model.addAttribute("mode", "add"); // <- thêm dòng này
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.get(id).orElse(new User()));
        model.addAttribute("users", userService.listAll());
        model.addAttribute("mode", "update"); // <- thêm dòng này
        return "index";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, Model model) {
        String msg = userService.createUser(user);
        model.addAttribute("message", msg);
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listAll());
        model.addAttribute("mode", "add"); // <- thêm dòng này
        return "index";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user, Model model) {
        String msg = userService.updateUser(user);
        model.addAttribute("message", msg);
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listAll());
        model.addAttribute("mode", "add"); // <- reset lại form về add
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.search(keyword));
        model.addAttribute("mode", "add"); // <- thêm dòng này
        return "index";
    }
}