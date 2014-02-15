package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Pizza;

import java.util.List;

public interface PizzaDao {

	long addPizza(Pizza pizza);

    void setCook(long id, String name);

	void updatePizza(Pizza pizza);

	void deletePizza(Pizza pizza);

	Pizza loadById(long id);

	List<Pizza> loadByOrder(long orderId);

    List<Pizza> loadAll();
}
