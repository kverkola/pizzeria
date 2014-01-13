package ua.opu.dl.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.OrderServise;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrderServise orderService;

	/**
	 * Test controller, load data from DAO and count total order price
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

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
	public String menu(ModelMap model, HttpSession session) {

		session.setAttribute("pizzasInOrder", orderService.loadById(10)
				.getPizzas());

		model.addAttribute("menu", pizzaService.loadAll());

		return "menu";
	}

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback() {

        return "feedback";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {

		return "about";
	}

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(ModelMap model) {

		Order order = orderService.loadById(134);

		model.addAttribute("order", order);

		return "order";
	}

}
