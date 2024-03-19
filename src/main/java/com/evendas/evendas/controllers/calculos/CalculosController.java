package com.evendas.evendas.controllers.calculos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/calculos")
public class CalculosController {

    @GetMapping
    public ModelAndView index()throws Exception {
        ModelAndView modelAndView = new ModelAndView("calculos/index");
        return modelAndView;
    }

}
