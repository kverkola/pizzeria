package ua.opu.dl.pizzeria.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.dao.impl.AdditionalDaoImpl;
import ua.opu.dl.pizzeria.model.Additional;
import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;
import ua.opu.dl.pizzeria.model.Product;
import ua.opu.dl.pizzeria.service.OrderService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {
	@Autowired
	private AdditionalDaoImpl addDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private PizzaDao pizzaDao;

	@Override
	public void addOrder(Order order) {

		orderDao.addOrder(order);
	}

	private Order order;

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
		order = orderDao.loadById(id);
		List<Product> products = new ArrayList<Product>();
		products.addAll(pizzaDao.loadByOrder(id));
		products.addAll(addDao.loadAdditionalsByOrder(id));
		order.setProducts(products);
		return order;
	}

	@Override
	public List<Order> loadAllOrder() {

		return orderDao.loadAllOrders();
	}
}
