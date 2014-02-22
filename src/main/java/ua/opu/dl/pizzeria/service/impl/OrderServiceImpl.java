package ua.opu.dl.pizzeria.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.OrderDao;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.dao.impl.AdditionalDaoImpl;
import ua.opu.dl.pizzeria.model.*;
import ua.opu.dl.pizzeria.service.AdditionalService;
import ua.opu.dl.pizzeria.service.CustomerService;
import ua.opu.dl.pizzeria.service.OrderService;
import ua.opu.dl.pizzeria.service.PizzaService;

import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	CustomerService customerService;
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private AdditionalService additionalService;

	private List<Product> products;
	private List<Order> orders;
	private List<Customer> customers;
	private Customer customer;

	@Override
	public void addOrder(Order order) {
		long id = orderDao.addOrder(order);

		List<Pizza> pizzas = order.getProducts(Pizza.class);
		List<Additional> additionals = order.getProducts(Additional.class);
		for (Pizza pizza : pizzas) {

			for (int i = 1; i <= pizza.getQuantity(); i++) {

				pizza.setOrderId(id);

				pizzaService.addPizza(pizza);
			}

		}

		for (Additional additional : additionals) {

			for (int i = 1; i <= additional.getQuantity(); i++) {
				additional.setOrderId(id);
				additionalService.addAdditional(additional);
			}
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
		if(order!=null){
        products = new ArrayList<Product>();
		products.addAll(pizzaService.loadByOrder(id));
		products.addAll(additionalService.loadByOrder(id));
		order.setProducts(products);
		customer = customerService.loadByOrderId(order.getId());
		order.setCustomer(customer);}
		return order;
	}

	@Override
	public List<Order> loadAllOrder() {

		return orderDao.loadAllOrders();
	}

	@Override
	public List<Order> loadByPhone(String phone) {
		
		customers=customerService.loadByPhone(phone);
		products = new ArrayList<Product>();
		orders = new ArrayList<Order>();
		for (Customer customer : customers) {
			order=orderDao.loadByCustomer(customer.getId());
			products.addAll(pizzaService.loadByOrder(order.getId()));
			products.addAll(additionalService.loadByOrder(order.getId()));
			order.setProducts(products);
			order.setCustomer(customer);
			orders.add(order);
		}
		return orders;
	}

	@Override
	public List<Order> loadAllByStatus(Status status) {

        List<Order> orders = orderDao.loadAllByStatus(status);

       // List<Product> products;

		for (Order order : orders) {

            products = new ArrayList();

			products.addAll(pizzaService.loadByOrder(order.getId()));
			products.addAll(additionalService.loadByOrder(order.getId()));

            order.setProducts(products);

            Customer customer = customerService.loadByOrderId(order.getId());
			order.setCustomer(customer);
		}

		return orders;
	}

	@Override
	public List<Order> loadOrdersForCook() {
		orders=orderDao.loadOrdersForCook();
		 // List<Product> products;

			for (Order order : orders) {

	            products = new ArrayList();

				products.addAll(pizzaService.loadByOrder(order.getId()));
				products.addAll(additionalService.loadByOrder(order.getId()));

	            order.setProducts(products);

	            Customer customer = customerService.loadByOrderId(order.getId());
				order.setCustomer(customer);
			}
		return  orders;
		
	}
}
