package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Order;

public interface OrderService {

	void addOrder(Order order);

	void updateOrder(Order order);

	void deleteOrder(Order order);

	Order loadById(long id);

	List<Order> loadByPhone(String phone);

	List<Order> loadAllOrder();
}
