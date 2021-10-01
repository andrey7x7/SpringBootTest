package com.example.controllers;

import com.example.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means that this class is a Controller
@RequestMapping
public class PageController {

    @GetMapping(path="/")
    public String index() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)principal;
        return "pages/index";
    }

    @GetMapping(path="/admin")
    public String admin() {
        return "pages/admin";
    }
}
