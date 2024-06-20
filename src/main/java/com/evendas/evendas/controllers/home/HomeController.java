package com.evendas.evendas.controllers.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("home/index");
        return modelAndView;
    }


//
}
