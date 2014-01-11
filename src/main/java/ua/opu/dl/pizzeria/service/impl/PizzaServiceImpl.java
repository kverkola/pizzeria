package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.PizzaService;

public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaDao pizzaDao;

	@Override
	public Pizza loadById(Integer id) {
		return pizzaDao.loadById(id);
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

		return pizzaDao.loadByOrder(orderId);
	}
}
