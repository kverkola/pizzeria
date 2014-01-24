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

	private Integer key;
	private Pizza pizza;

	@RequestMapping(value = "/countPlus/{ingredientName}", method = RequestMethod.GET)
	public String actionPlus(
			@PathVariable("ingredientName") String ingredientName,
			ModelMap model, HttpSession session) {

		pizza = (Pizza) session.getAttribute("pizzaChanged");
		Map<Ingredient, Integer> ingrMap = pizza.getMap();

		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			if (ingr.getName().equals(ingredientName)) {
				if (key == 20) {
					break;
				}
				ingrMap.put(ingr, key + 1);
				break;
			}

		}
		
		pizza.countTotalPrice();
		model.addAttribute("pizza", pizza);
		return "/addIngredients";
	}

	@RequestMapping(value = "/countMinus/{ingredientName}", method = RequestMethod.GET)
	public String actionMinus(
			@PathVariable("ingredientName") String ingredientName,
			ModelMap model, HttpSession session) {

		pizza = (Pizza) session.getAttribute("pizzaChanged");
		Map<Ingredient, Integer> ingrMap = pizza.getMap();

		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			if (ingr.getName().equals(ingredientName)) {
				if (key == 0) {
					break;
				}

				ingrMap.put(ingr, key - 1);
				break;
			}

		}
		
		pizza.countTotalPrice();
		model.addAttribute("pizza", pizza);
		return "/addIngredients";
	}

	@RequestMapping(value = "/addIngredients/{id}", method = RequestMethod.GET)
	public String addIngredients(@PathVariable("id") Integer id,
			ModelMap model, HttpSession session) {

		pizza = (Pizza) session.getAttribute("pizzaChanged");
		if (pizza == null) {
			pizza = pizzaService.loadById(id);
			session.setAttribute("pizzaChanged", pizza);

		}

		model.addAttribute("pizza", pizza);

		return "addIngredients";
	}
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(ModelMap model,@PathVariable("id") Integer id){
		
		pizza=pizzaService.loadById(id);
		model.addAttribute("pizza",pizza);
		return "/addIngredients";	
	}
	
	
}
