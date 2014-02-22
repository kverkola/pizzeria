package ua.opu.dl.pizzeria.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;
import ua.opu.dl.pizzeria.service.UserService;

@Controller
public class CookController {

	private static final Logger LOG = LoggerFactory
			.getLogger(CookController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/cook/assign/{pizzaId},{orderId}", method = RequestMethod.GET)
	public String assign(@PathVariable("pizzaId") long id,
			@PathVariable("orderId") long orderId, Principal principal) {
		LOG.info("Pizza assigned to: " + principal.getName());
		Users cook = userService.loadByLogin(principal.getName());
		pizzaService.setCook(id, cook.getId());
		pizzaService.UpdateStatus(id, Status.IN_WORK);
		Order order = orderService.loadById(orderId);
		if (order.getStatus().toString().equals("PRE_ORDER")) {
			order.setStatus(Status.IN_WORK);
			orderService.updateOrder(order);
		}
		return "redirect:/cook";
	}

	@RequestMapping(value = "/cook/finish-pizza/{pizzaId}", method = RequestMethod.GET)
	public String finish(@PathVariable("pizzaId") long id, Principal principal) {
		pizzaService.UpdateStatus(id, Status.CLOSE);

		return "redirect:/cook";
	}
}
