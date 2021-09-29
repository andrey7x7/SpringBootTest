package com.example.controllers;

import com.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means that this class is a Controller
@RequestMapping
public class PageController {

    @GetMapping(path="/")
    public String index() {
        return "pages/index";
    }

    @GetMapping(path="/admin")
    public String admin() {
        return "pages/admin";
    }
}
