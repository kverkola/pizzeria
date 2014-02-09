package ua.opu.dl.pizzeria.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.dao.impl.AdditionalDaoImpl;
import ua.opu.dl.pizzeria.model.*;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private AdditionalService additionalService;

	private List<Product> products;
	private List<Order> orders;

	@Override
	public void addOrder(Order order) {
		long id = orderDao.addOrder(order);
		List<Pizza> pizzas = order.getProducts(Pizza.class);
		List<Additional> additionals = order.getProducts(Additional.class);
		for (Pizza pizza : pizzas) {
			pizza.setOrderId(id);
			pizzaService.addPizza(pizza);
		}
		for (Additional additional : additionals) {
			additional.setOrderId(id);
			additionalService.addAdditional(additional);
		}

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
	public Order loadById(long id) {
		order = orderDao.loadById(id);
		products = new ArrayList<Product>();
		products.addAll(pizzaService.loadByOrder(id));
		products.addAll(additionalService.loadByOrder(id));
		order.setProducts(products);

		return order;
	}

	@Override
	public List<Order> loadAllOrder() {

		return orderDao.loadAllOrders();
	}

	@Override
	public List<Order> loadByPhone(String phone) {
		orders = orderDao.loadByPhone(phone);
		products = new ArrayList<Product>();
		for (Order order : orders) {
			products.addAll(pizzaService.loadByOrder(order.getId()));
			products.addAll(additionalService.loadByOrder(order.getId()));
			order.setProducts(products);
		}
		return orders;
	}

    @Override
    public List<Order> loadAllByStatus(Status status) {
         orderDao.loadAllByStatus(status);
        products = new ArrayList<Product>();
		for (Order order : orders) {
			products.addAll(pizzaService.loadByOrder(order.getId()));
			products.addAll(additionalService.loadByOrder(order.getId()));
			order.setProducts(products);
		}
		return orders;
        
        
        
    }
}
