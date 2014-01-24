package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.service.OrderService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {

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
    public void updatePrice(Order order) {

        Double totalPrice = 0.0;
        Map<Additional, Integer> additions = order.getAdditional();
        if (additions != null) {
            for (Additional a : additions.keySet()) {
                totalPrice += a.getPrice() * additions.get(a);
            }
        }

        Map<Pizza, Integer> pizzas = order.getPizzas();
        if (pizzas != null) {
            for (Pizza p : pizzas.keySet()) {
                totalPrice += p.getPrice() * pizzas.get(p);
            }
        }
        order.setPrice(totalPrice);
    }
}
