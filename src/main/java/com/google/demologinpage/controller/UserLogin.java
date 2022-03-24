package com.google.demologinpage.controller;

import com.google.demologinpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class UserLogin {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String password, HttpSession httpSession, Model model) {
        if (userRepository.findById(email).isPresent()) {
            if (userRepository.findById(email).get().getPassword().equals(password)) {
                httpSession.setAttribute("userEmail", email);
                return "redirect:/index";
            } else {
                model.addAttribute("Password", "false");
            }
        } else {
            model.addAttribute("Email", "notFound");
        }
        return "login";
    }
}
