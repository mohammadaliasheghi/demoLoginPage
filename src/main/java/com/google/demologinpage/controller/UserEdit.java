package com.google.demologinpage.controller;

import com.google.demologinpage.domain.User;
import com.google.demologinpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserEdit {

    private UserRepository userRepository;
    private User user;

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/edit")
    public String getEdit(HttpSession httpSession, Model model) {
        if (userRepository.findById(String.valueOf(httpSession.getAttribute("userEmail"))).isPresent()) {
            user = userRepository.findById(String.valueOf(httpSession.getAttribute("userEmail"))).get();
            model.addAttribute("user", user);
            return "edit";
        }
        return "redirect:/index";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute @Valid User user, BindingResult bindingResult, HttpSession httpSession, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        if (httpSession.getAttribute("userEmail").equals(user.getEmail())) {
            userRepository.save(user);
        } else {
            if (userRepository.findById(user.getEmail()).isPresent()) {
                model.addAttribute("userEmail", "duplicate");
                return "edit";
            } else {
                userRepository.deleteById(String.valueOf(httpSession.getAttribute("userEmail")));
                userRepository.save(user);
                httpSession.setAttribute("userEmail", user.getEmail());
            }
        }

        return "redirect:/edit";
    }

    @PostMapping("/goBack")
    public String getBack() {
        return "redirect:/index";
    }
}
