package ua.opu.dl.pizzeria.dao;

import java.util.List;


import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;

public interface OrderDao {

    long addOrder(Order order);

	void updateOrder(Order order);

	void deleteOrder(Order order);

	Order loadById(long id);
	Order loadByCustomer(long Id);

	List<Order> loadAllOrders();

    List<Order> loadAllByStatus(Status status);
}
