package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Pizza;

public interface PizzaService {

	void addPizza(Pizza pizza);

    void setCook(long id, long cookId);

	void updatePizza(Pizza pizza);

	void deletePizza(Pizza pizza);

	Pizza loadById(long id);

	List<Pizza> loadByOrder(long orderId);

    List<Pizza> loadAll();
}
