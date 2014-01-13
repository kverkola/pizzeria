package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Pizza;

public interface PizzaService {

	void addPizza(Pizza pizza);

	void updatePizza(Pizza pizza);

	void deletePizza(Pizza pizza);

	Pizza loadById(Integer id);

    Pizza loadByName(String name);

	List<Pizza> loadByOrder(Integer orderId);

    List<Pizza> loadAll();
}
