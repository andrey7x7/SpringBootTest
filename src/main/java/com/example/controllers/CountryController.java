package com.example.controllers;

import com.example.model.Country;
import com.example.model.CountryRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means that this class is a Controller
@RequestMapping(path="/country")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    //помечаем как метод для вызова странички по умолчанию (тут /country)
    @GetMapping()
    public String index(Model model) {
        //получим все из DAO и передадим на отображение в представление (view)
        Iterable<Country> allCountry = countryRepository.findAll();
        model.addAttribute("lot_country", allCountry);
        return "country/index";
    }

    //помечаем как метод для вызова странички /author/new
    @GetMapping("/new")
    public String newCountry(@ModelAttribute("country") Country country) {
        return "country/new";
    }


    //Помечаем метод как POST на него перейдем после вызова POST метода с формы
    //принимаем с формы объект person и передаем в DAO
    //redirect - обязательно просто перейти на /author нельзя
    @PostMapping(path="/add")
    public String create(@ModelAttribute("country") Country country) {
        countryRepository.save(country);
        return "redirect:/country";
    }
}