package ua.opu.dl.pizzeria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.service.PizzaService;

@Controller
@RequestMapping(value = "/cook")
public class CookController {

	@Autowired
	private PizzaService pizzaService;
	

}
