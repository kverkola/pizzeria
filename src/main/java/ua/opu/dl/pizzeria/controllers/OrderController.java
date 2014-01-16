package ua.opu.dl.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderServise;
import ua.opu.dl.pizzeria.service.PizzaService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private OrderServise orderService;
	@Autowired
	private AdditionalService AdditionalService;

	@RequestMapping(value = "/add-pizza/{name}", method = RequestMethod.GET)
	public String addPizza(@PathVariable("name") String name,
			HttpSession session) {

		Order order = (Order) session.getAttribute("order");

		if (order == null) {
			order = new Order();
			order.setPizzas(new HashMap<Pizza, Integer>());
		}

		orderService.addPizza(order, pizzaService.loadByName(name));
		session.setAttribute("order", order);
		session.setAttribute("pizzasInOrder", order.getPizzas());

		return "redirect:/menu";
	}
	@RequestMapping(value = "/add-additional/{name}", method = RequestMethod.GET)
	public String addAdditional(@PathVariable("name") String name,
			HttpSession session) {
		
		Order order = (Order) session.getAttribute("order");
		
		if (order == null) {
			order = new Order();
			order.setAdditional(new HashMap<Additional, Integer>());
		}
		
		orderService.addAdditional(order, AdditionalService.loadByName(name));
		session.setAttribute("order", order);
		session.setAttribute("AdditionalInOrder", order.getAdditional());
		
		return "redirect:/Additional";
	}

	@RequestMapping(value = "/make-order", method = RequestMethod.GET)
	public String makeOrder() {
		return "completeOrder";
	}

	@RequestMapping(value = "/change-count", method = RequestMethod.POST, params = {
			"name", "value" })
	public String changeCount(@RequestParam String name,
			@RequestParam Integer value) {

		// recalculate price and change count

		return "redirect:/order/make-order";
	}

	@RequestMapping(value = "/searchOrder", method = RequestMethod.GET)
	public String order(@RequestParam("orderId") Integer orderId, HttpSession session,ModelMap model) {

		Order order = orderService.loadById(orderId);

		session.setAttribute("orderById",order);
		model.addAttribute("order", order);
		return "searchOrder";

	}
	
	@RequestMapping(value = "/showIngredient/{pizzaId}", method = RequestMethod.GET)
	public String showIngredient(@PathVariable("pizzaId") Integer pizzaId,
			HttpSession session,ModelMap model) {
	Pizza pizza=pizzaService.loadById(pizzaId);
		
		model.addAttribute("pizza", pizza);
		
		return "redirect:/ingredient";
	}

}
