package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Pizza;

import java.util.List;

public interface PizzaDao {

	void addPizza(Pizza pizza);

	void updatePizza(Pizza pizza);

	void deletePizza(Pizza pizza);

	Pizza loadById(long id);

	List<Pizza> loadByOrder(long orderId);

    List<Pizza> loadAll();
}
