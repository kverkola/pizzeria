package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.model.Users;

import java.util.List;

public interface PizzaDao {

	long addPizza(Pizza pizza);

    void setCook(long id, long Cookid);

	void updatePizza(Pizza pizza);

	void deletePizza(Pizza pizza);

	Pizza loadById(long id);

	List<Pizza> loadByOrder(long orderId);

    List<Pizza> loadAll();
   
}
