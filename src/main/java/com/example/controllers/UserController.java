package com.example.controllers;

import com.example.model.Country;
import com.example.model.CountryRepository;
import org.springframework.security.core.Authentication;
import com.example.model.User;
import com.example.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    //помечаем как метод для вызова странички по умолчанию (тут /people)
    @GetMapping()
    public String index (Model model) {
        //получим всех людей из DAO и передадим на отображение в представление (view)
        Iterable<User> allUsers = userRepository.findAll();
        model.addAttribute("users", allUsers);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("login", auth.getName());
        return "user/index";
    }

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    //помечаем как метод для вызова странички /author/id (id передаем как параметр)
    @GetMapping(path="/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //Получим человека по id из DAO и передадим на отображение в представление
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "user/show";
    }

    //помечаем как метод для вызова странички /author/new
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        Iterable<Country> allCountry = countryRepository.findAll();
        model.addAttribute("lot_country", allCountry);
        return "user/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        Iterable<Country> allCountry = countryRepository.findAll();
        model.addAttribute("lot_country", allCountry);
        return "user/edit";
    }

    //Помечаем метод как POST на него перейдем после вызова POST метода с формы
    //принимаем с формы объект person и передаем в DAO
    //redirect - обязательно просто перейти на /author нельзя
    @PostMapping(path="/add")
    public String create(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/user";
    }

    @PostMapping("/del/{id}")
    public String delete(@PathVariable("id") int id ){
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    //Помечаем метод как PATH на него перейдем после вызова PATH метода с формы
    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id ) {
        userRepository.save(user);
        return "redirect:/user";
    }
}