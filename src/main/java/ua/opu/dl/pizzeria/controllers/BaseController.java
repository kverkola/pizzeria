package ua.opu.dl.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderServise;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class BaseController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrderServise orderService;
	@Autowired
	private AdditionalService additionalService;

	/**
	 * Test controller, load data from DAO and count total order price
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model, HttpSession session) {

		Pizza pizza = pizzaService.loadById(10);

		Double totalPrice = pizza.getPrice();
		for (Ingredient ingredient : pizza.getMap().keySet()) {
			totalPrice += ingredient.getPrice();
		}

		model.addAttribute("pizza", pizza);
		model.addAttribute("totalPrice", totalPrice);

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
    public String about() {

		return "about";
	}


	}


