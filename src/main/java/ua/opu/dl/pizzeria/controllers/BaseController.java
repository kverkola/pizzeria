package ua.opu.dl.pizzeria.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.IngredientService;
import ua.opu.dl.pizzeria.service.PizzaService;

@Controller
public class BaseController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private AdditionalService additionalService;
	@Autowired
	private IngredientService ingredientService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		Locale.setDefault(Locale.ENGLISH);

		return "index";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(ModelMap model) {

		model.addAttribute("menu", pizzaService.loadByOrder(0));

		return "menu";
	}

	@RequestMapping(value = "/Additional", method = RequestMethod.GET)
	public String additional(ModelMap model) {

		model.addAttribute("add", additionalService.loadByOrder(0));// изменить
																	// на 0
																	// позже

		return "Additional";
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public String feedback() {

		return "feedback";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(ModelMap model) {
		// test page

		return "about";
	}
}
