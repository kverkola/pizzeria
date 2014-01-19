package ua.opu.dl.pizzeria.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.PizzaService;

@Controller
@RequestMapping(value = "/ingredients")
public class IngredientsController {
	@Autowired
	private PizzaService pizzaService;

	@RequestMapping(value = "/count/{ingredientName}/{action}", method = RequestMethod.GET)
	public String getCount(
			@PathVariable("ingredientName") String ingredientName,@PathVariable("action") String action,
			ModelMap model, HttpSession session) {

		Pizza pizza = (Pizza) session.getAttribute("pizzaChanged");
		Map<Ingredient, Integer> ingrMap = pizza.getMap();
		Integer key = 0;
		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			if (ingr.getName().equals(ingredientName)) {
				if(action.equals("up")){
				ingrMap.put(ingr, key + 1);}
				else{ingrMap.put(ingr, key - 1);}
			}

		}

		model.addAttribute("pizza", pizza);
		return "/addIngredients";
	}

	@RequestMapping(value = "/addIngredients/{id}", method = RequestMethod.GET)
	public String addIngredients(@PathVariable("id") Integer id,
			ModelMap model, HttpSession session) {
		Pizza pizza;
		pizza = (Pizza) session.getAttribute("pizzaChanged");
		if (pizza == null) {
			pizza = pizzaService.loadById(id);
			session.setAttribute("pizzaChanged", pizza);

		}

		model.addAttribute("pizza", pizza);

		return "addIngredients";
	}

}
