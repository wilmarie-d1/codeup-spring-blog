package com.codeup.codeupspringblog.models.controllers;

import com.codeup.codeupspringblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.codeup.codeupspringblog.services.UserDaoService;


@Controller
public class UserController {

    private UserDaoService userDao;
    public UserController(UserDaoService userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        userDao.registerUser(user);
        return "redirect:/login";
    }
}
