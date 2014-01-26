package ua.opu.dl.pizzeria.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

		model.addAttribute("menu", pizzaService.loadAll());

		return "menu";
	}

    @RequestMapping(value = "/Additional", method = RequestMethod.GET)
	public String additional(ModelMap model) {
		
		model.addAttribute("add", additionalService.AllAdditionals());

		return "Additional";
	}

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback() {

        return "feedback";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(ModelMap model) {
//test page
    	
    	
    	model.addAttribute("ingr", ingredientService.loadById(8));
    	
		return "about";
	}
}


