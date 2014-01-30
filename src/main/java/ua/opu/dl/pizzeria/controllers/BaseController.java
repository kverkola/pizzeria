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

		model.addAttribute("menu", pizzaService.loadByOrder(0));

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
    	List<Pizza> pizzaList = new ArrayList();
    	
   			Map<Ingredient, Integer> ingredientMap = new HashMap();
    			ingredientMap.put(new Ingredient("Ham", 100, 30), 1);
    			ingredientMap.put(new Ingredient("Vegetables", 80, 10), 1);
    			ingredientMap.put(new Ingredient("Cheese", 90, 20), 1);
    			ingredientMap.put(new Ingredient("Sauce", 60, 15), 1);
    			ingredientMap.put(new Ingredient("Crust", 300, 30), 1);
    	

    			String[] imgs = { "chikenita_middle.png", "img_2.png",
    					"pizza_middle.png", "tanu_mini.png" };
    			String[] descrips = {
    					"Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
    					"Aenean commodo ligula eget dolor. Aenean massa.",
    					"Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
    					"Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim." };
    	
   			for (int i = 0; i < imgs.length; i++) {
    				pizzaList.add(new Pizza("TestPizza " + i, ingredientMap, imgs[i],
   						descrips[i], i, 45));
    			}
    	pizzaService.addPizza(pizzaList.get(1));
    	
		return "about";
	}
}


