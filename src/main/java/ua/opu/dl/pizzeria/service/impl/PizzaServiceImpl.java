package ua.opu.dl.pizzeria.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.IngredientDao;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.model.Status;
import ua.opu.dl.pizzeria.model.Users;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.PizzaService;
import ua.opu.dl.pizzeria.service.UserService;

public class PizzaServiceImpl implements PizzaService {
	@Autowired
	private UserService userService;
	@Autowired
	private PizzaDao pizzaDao;
	@Autowired
	private IngredientServiceImpl ingredientService;
	private Pizza pizza;
	private Users cook;
	private HashMap<Ingredient, Integer> ingredientsMap;
	private List<Ingredient> listIngredients;

	@Override
	public Pizza loadById(long id) {
		ingredientsMap = new HashMap<Ingredient, Integer>();
		pizza = pizzaDao.loadById(id);
		cook = userService.loadCookByPizzaId(pizza.getId());
		listIngredients = ingredientService.loadByPizza(pizza.getId());
		Integer am;
		for (Ingredient i : listIngredients) {
			am = ingredientsMap.get(i);
			ingredientsMap.put(i, am == null ? 1 : am + 1);
		}
		pizza.setMap(ingredientsMap);
		if (cook == null) {
			cook = new Users();
		}
		pizza.setCook(cook);
        pizza.incrementQuantity();

		return pizza;
	}

	@Override
	public void addPizza(Pizza pizza) {

		Map<Ingredient, Integer> ingredients = pizza.getMap();
		long id = pizzaDao.addPizza(pizza);
		int count;

		for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
			count = entry.getValue();
			for (int j = 1; j <= count; j++) {
				Ingredient i = entry.getKey();
				i.setPizzaId(id);
				ingredientService.addIngredient(i);
			}
		}
	}

	@Override
	public void updatePizza(Pizza pizza) {

		pizzaDao.updatePizza(pizza);
	}

	@Override
	public void deletePizza(Pizza pizza) {

		pizzaDao.deletePizza(pizza);
	}

	@Override
	public List<Pizza> loadByOrder(long orderId) {
		List<Pizza> listPizzas = pizzaDao.loadByOrder(orderId);
		for (int a = 0; a < listPizzas.size(); a++) {
			pizza = listPizzas.get(a);
			cook = userService.loadCookByPizzaId(pizza.getId());
			listIngredients = ingredientService.loadByPizza(pizza.getId());
			ingredientsMap = new HashMap<Ingredient, Integer>();
			Integer am;
			for (Ingredient i : listIngredients) {
				am = ingredientsMap.get(i);
				ingredientsMap.put(i, am == null ? 1 : am + 1);
			}
			pizza.setMap(ingredientsMap);
			if (cook == null) {
				cook = new Users();
			}
			pizza.setCook(cook);
		}

		return listPizzas;
	}

	@Override
	public List<Pizza> loadAll() {

		return pizzaDao.loadAll();
	}

	@Override
	public void setCook(long id, long cookId) {
		pizzaDao.setCook(id, cookId);
		
	}

	@Override
	public void UpdateStatus(long pizzaId,Status status) {
	
		pizzaDao.UpdateStatus(pizzaId,status);
	}
}
