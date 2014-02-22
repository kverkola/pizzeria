package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;

public interface OrderService {

	void addOrder(Order order);

	void updateOrder(Order order);

	void deleteOrder(Order order);

	Order loadById(long id);

	List<Order> loadByPhone(String phone);

	List<Order> loadAllOrder();

	List<Order> loadOrdersForCook();

	List<Order> loadAllByStatus(Status status);
}
