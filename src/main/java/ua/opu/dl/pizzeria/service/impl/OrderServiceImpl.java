package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.service.OrderServise;

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

}
