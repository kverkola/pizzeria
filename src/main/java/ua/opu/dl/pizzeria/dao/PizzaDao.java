package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Pizza;

import java.util.List;

public interface PizzaDao {

    void insert(Pizza pizza);
    void update(Pizza pizza);
    void delete(Pizza pizza);
    Pizza loadById(Integer id);
    List<Pizza> loadByOrder(Integer orderId);
}
