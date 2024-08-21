package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    LoginService loginService;

    // Display login form
    @GetMapping("login")
    public String showLoginForm() {
        return "login"; // This corresponds to src/main/resources/templates/login.html
    }

    // Handle login form submission
    @PostMapping("login")
    public String handleLogin(@ModelAttribute User user, HttpSession session) {
        System.out.println(user.getPid());
        System.out.println(user.getPassword());
        Boolean login = loginService.login(user);
        login = false;
        session.setAttribute("result", login);

        if (login) {
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}