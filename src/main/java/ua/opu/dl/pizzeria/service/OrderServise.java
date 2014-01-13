package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Order;
import ua.opu.dl.pizzeria.model.Pizza;

public interface OrderServise {

    void addPizza(Order order, Pizza pizza);

    void addOrder(Order order);

	void updateOrder(Order order);

	void deleteOrder(Order order);

	Order loadById(Integer id);

	List<Order> loadAllOrder();
}
