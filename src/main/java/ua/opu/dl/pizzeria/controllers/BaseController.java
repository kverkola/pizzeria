package ua.opu.dl.pizzeria.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.PizzaService;

@Controller
public class BaseController {

	@Autowired
	private PizzaService pizzaService;

	@Autowired
	private AdditionalService additionalService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		Locale.setDefault(Locale.ENGLISH);
		return "index";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(ModelMap model) {

		model.addAttribute("menu", pizzaService.loadByOrder(15));

		return "menu";
	}

    @RequestMapping(value = "/Additional", method = RequestMethod.GET)
	public String additional(ModelMap model) {
		
		model.addAttribute("add", additionalService.loadByOrder(0));//изменить на 0 позже

		return "Additional";
	}

    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedback() {

        return "feedback";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(ModelMap model) {
//test page
    	Additional additional=new Additional("pepsi",6,"pepsi.png");
    	additional.setOrderId(0);
    	additionalService.addAdditional(additional);
    	
		return "about";
	}
}


