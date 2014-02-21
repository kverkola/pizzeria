package ua.opu.dl.pizzeria.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.model.Users;
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

	@RequestMapping(value = "/cook/assign/{pizzaId}", method = RequestMethod.GET)
	public String assign(@PathVariable("pizzaId") long id, Principal principal) {
		LOG.info("Pizza assigned to: " + principal.getName());
		Users cook = userService.loadByLogin(principal.getName());
		pizzaService.setCook(id, cook.getId());	
		return "redirect:/cook";
	}
}
