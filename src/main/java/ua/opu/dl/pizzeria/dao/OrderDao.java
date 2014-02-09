package ua.opu.dl.pizzeria.dao;

import java.util.List;


import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Status;

public interface OrderDao {

    long addOrder(Order order);

	void updateOrder(Order order);

	void deleteOrder(Order order);

	Order loadById(long id);
	List<Order> loadByPhone(String phone);

	List<Order> loadAllOrders();

    List<Order> loadAllByStatus(Status status);
}
