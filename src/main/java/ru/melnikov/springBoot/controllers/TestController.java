package ru.melnikov.springBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.melnikov.springBoot.security.PersonUserDetails;

@Controller
public class TestController {


    @GetMapping("/hello")
    public String first(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonUserDetails personUserDetails = (PersonUserDetails) authentication.getPrincipal();
        model.addAttribute("userObject",personUserDetails);
        return "hello";
    }
}
