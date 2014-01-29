package ua.opu.dl.pizzeria.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(IngredientsController.class);

	@Autowired
	private PizzaService pizzaService;

	private Integer key;
	private Pizza pizza;

	@RequestMapping(value = "/countPlus/{ingredientName}", method = RequestMethod.GET)
	public String actionPlus(
			@PathVariable("ingredientName") String ingredientName,
			ModelMap model, HttpSession session) {

		pizza = (Pizza) session.getAttribute("customPizza");
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

		pizza = (Pizza) session.getAttribute("customPizza");
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

        Pizza pizza = pizzaService.loadById(id);

        LOG.info("Loaded ingredients for pizza: " + pizza.getName());
        for (Map.Entry<Ingredient, Integer> entry : pizza.getMap().entrySet()) {
            LOG.info("Ingredient: " + entry.getKey().getName() + " value: " + entry.getValue());
        }

        session.setAttribute("customPizza", pizza);
		model.addAttribute("pizza", pizza);

		return "addIngredients";
	}

	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(ModelMap model,@PathVariable("id") Integer id){
		
		pizza = pizzaService.loadById(id);
		model.addAttribute("pizza",pizza);
		return "/addIngredients";	
	}
}
