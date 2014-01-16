package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.OrderServise;

import java.util.Map;
import java.util.Map.Entry;

public class OrderServiceImpl implements OrderServise {
	@Autowired
	private OrderDao orderDao;

	@Override
	public void addOrder(Order order) {
		orderDao.addOrder(order);

	}

	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);

	}

	@Override
	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);

	}

	@Override
	public Order loadById(Integer id) {

		return orderDao.loadById(id);
	}

	@Override
	public List<Order> loadAllOrder() {

		return orderDao.loadAllOrders();
	}

    @Override
    public void addPizza(Order order, Pizza pizza) {

        Map<Pizza, Integer> pizzas = order.getPizzas();

        if (pizzas.containsKey(pizza)) {
            pizzas.put(pizza, pizzas.get(pizza) + 1);
        } else {
            pizzas.put(pizza, 1);
        }

        order.setPrice(order.getPrice() + pizza.getPrice());
    }

	@Override
	public void addAdditional(Order order, Additional additional) {

        Map<Additional, Integer> additions = order.getAdditional();

        if (additions.containsKey(additional)) {
            additions.put(additional, additions.get(additional) + 1);
        } else {
            additions.put(additional, 1);
        }

        order.setPrice(order.getPrice() + additional.getPrice());
	}
}
