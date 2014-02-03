package ua.opu.dl.pizzeria.controllers;

import java.util.List;
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
import ua.opu.dl.pizzeria.service.impl.IngredientServiceImpl;

@Controller
@RequestMapping(value = "/ingredients")
public class IngredientsController {

	private static final Logger LOG = LoggerFactory
			.getLogger(IngredientsController.class);

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private IngredientServiceImpl ingredientService;
	private Integer key;
	private Pizza pizza;
	Map<Ingredient, Integer> ingrMap;

	@RequestMapping(value = "/countPlus/{id}", method = RequestMethod.GET)
	public String actionPlus(@PathVariable("id") long id, ModelMap model,
			HttpSession session) {

		pizza = (Pizza) session.getAttribute("customPizza");
		ingrMap = pizza.getMap();

		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			if (ingr.getId() == id) {
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

	@RequestMapping(value = "/countMinus/{id}", method = RequestMethod.GET)
	public String actionMinus(@PathVariable("id") long id, ModelMap model,
			HttpSession session) {

		pizza = (Pizza) session.getAttribute("customPizza");
		ingrMap = pizza.getMap();

		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			if (ingr.getId() == id) {
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
	public String addIngredients(@PathVariable("id") long id, ModelMap model,
			HttpSession session) {
		if (id == 0) {
			
			pizza = (Pizza) session.getAttribute("customPizza");
		} else
			pizza = pizzaService.loadById(id);

		LOG.info("Loaded ingredients for pizza: " + pizza.getName());
		for (Map.Entry<Ingredient, Integer> entry : pizza.getMap().entrySet()) {
			LOG.info("Ingredient: " + entry.getKey().getName() + " value: "
					+ entry.getValue());
		}

		session.setAttribute("customPizza", pizza);
		model.addAttribute("pizza", pizza);

		return "/addIngredients";
	}

	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(ModelMap model, @PathVariable("id") long id,
			HttpSession session) {

		pizza = pizzaService.loadById(id);
		session.setAttribute("customPizza", pizza);
		model.addAttribute("pizza", pizza);
		return "/addIngredients";
	}

	@RequestMapping(value = "/addNewIngr/{id}", method = RequestMethod.GET)
	public String UpgradePizza(ModelMap model, @PathVariable("id") long id,
			HttpSession session) {

		Ingredient ingredient = ingredientService.loadById(id);
		pizza = (Pizza) session.getAttribute("customPizza");
		ingrMap = pizza.getMap();
		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			if (ingr.getName().equals(ingredient.getName())) {
				if (key == 20) {
					break;
				}
				ingrMap.put(ingr, key + 1);
				break;
			}
		}
		ingrMap.put(ingredient, 1);
		session.setAttribute("customPizza", pizza);

		return "redirect:/ingredients/upgradePizza";
	}

	@RequestMapping(value = "/upgradePizza", method = RequestMethod.GET)
	public String upgradePizza(ModelMap model) {
		List<Ingredient> ingredients = ingredientService.loadByPizza(0);
		model.addAttribute("ingredients", ingredients);

		return "/upgradePizza";
	}

}
