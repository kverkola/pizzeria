package ua.opu.dl.pizzeria.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(ModelMap model) {

        model.addAttribute("message", "Spring + JSP Hello World!");
        return "hello";
    }
}