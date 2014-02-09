package ua.opu.dl.pizzeria.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.service.PizzaService;

@Controller
public class CookController {

    private static final Logger LOG = LoggerFactory.getLogger(CookController.class);

	@Autowired
	private PizzaService pizzaService;

    @RequestMapping(value = "/cook/assign", method = RequestMethod.GET)
    public String assign(Principal principal) {

        LOG.info("Pizza assigned to: " + principal.getName());

        return "redirect:/cook";
    }
}
