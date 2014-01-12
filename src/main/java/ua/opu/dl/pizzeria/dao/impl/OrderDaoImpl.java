package ua.opu.dl.pizzeria.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.model.Status;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void addOrder(Order order) {

	}

	@Override
	public void updateOrder(Order order) {

	}

	@Override
	public void deleteOrder(Order order) {

	}

	@Override
	public Order loadById(Integer id) {

		Map<Ingredient, Integer> ingredientMap = new HashMap<Ingredient, Integer>();
		Map<Pizza, Integer> pizzaMap = new HashMap<Pizza, Integer>();
		Map<Additional, Integer> addMap = new HashMap();
		ingredientMap.put(new Ingredient("Ham", 100, 30), 1);
		ingredientMap.put(new Ingredient("Vegetables", 80, 10), 1);
		ingredientMap.put(new Ingredient("Cheese", 90, 20), 1);
		ingredientMap.put(new Ingredient("Sauce", 60, 15), 1);
		ingredientMap.put(new Ingredient("Crust", 300, 30), 1);
		Pizza pizza = new Pizza("TestPizza", ingredientMap, 25);
		addMap.put(new Additional("pepsi", 10), 5);
		pizzaMap.put(pizza, 2);

		return new Order(pizzaMap, Status.IN_WORK, new Date(), new Date(), 136,
				addMap, "5656565676573");
	}

	@Override
	public List<Order> loadAllOrders() {

		return null;
	}

}
