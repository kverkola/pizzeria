package ua.opu.dl.pizzeria.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.IngredientDao;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.PizzaService;

public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaDao pizzaDao;
	@Autowired
	private IngredientDao ingredientDao;
	private Pizza pizza;
	private HashMap<Ingredient, Integer> ingredientsMap;
	private List<Ingredient> listIngredients;

	@Override
	public Pizza loadById(Integer id) {
		ingredientsMap = new HashMap<Ingredient, Integer>();
		pizza = pizzaDao.loadById(id);
		listIngredients = ingredientDao.loadIngredientsByPizza(pizza.getId());
		Integer am;
		for (Ingredient i : listIngredients) {
			am = ingredientsMap.get(i);
			
			//int count = Collections.frequency(listIngredients, i);
			ingredientsMap.put(i, am == null ? 1 : am + 1);
			//ingredientsMap.put(i, count);
			
		}
		pizza.setMap(ingredientsMap);
		return pizza;
	}

	@Override
	public void addPizza(Pizza pizza) {

		pizzaDao.addPizza(pizza);
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
	public List<Pizza> loadByOrder(Integer orderId) {
		List<Pizza> listPizzas = pizzaDao.loadByOrder(orderId);
		for (int a = 0; a < listPizzas.size(); a++) {
			pizza = listPizzas.get(a);
			listIngredients = ingredientDao.loadIngredientsByPizza(pizza
					.getId());
			ingredientsMap = new HashMap<Ingredient, Integer>();
			Integer am;
			for (Ingredient i : listIngredients) {
				am = ingredientsMap.get(i);
				ingredientsMap.put(i, am == null ? 1 : am + 1);
			}
			pizza.setMap(ingredientsMap);

		}

		return listPizzas;
	}

	@Override
	public List<Pizza> loadAll() {

		return pizzaDao.loadAll();
	}
}
