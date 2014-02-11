package ua.opu.dl.pizzeria.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;
import ua.opu.dl.pizzeria.service.impl.IngredientServiceImpl;

@Controller
public class BaseController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private AdditionalService additionalService;

    @Autowired
    private OrderService orderService;

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

	@RequestMapping(value = "/additional", method = RequestMethod.GET)
	public String additional(ModelMap model) {

		model.addAttribute("add", additionalService.loadByOrder(0));
		return "additions";
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public String feedback() {

		return "feedback";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {

		return "about";
	}

	@RequestMapping(value = "/cook", method = RequestMethod.GET)
	public String cook(ModelMap model) {

        List<Pizza> pizzas = new ArrayList();

        for (Order order : orderService.loadAllByStatus(Status.IN_WORK)) {
            for (Pizza pizza : order.getProducts(Pizza.class)) {
                pizzas.add(pizza);
            }
        }

        model.addAttribute("pizzas", pizzas);
		return "cook";
	}
	@RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
	public String admin(ModelMap model) {

      
		return "adminPanel";
	}
}
