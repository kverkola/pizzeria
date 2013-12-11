package ua.opu.dl.pizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import ua.opu.dl.pizzeria.dao.PizzaDao;
import ua.opu.dl.pizzeria.model.Pizza;

public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaDao pizzaDao;

    @Override
    public Pizza loadById(Integer id) {
        return pizzaDao.loadById(id);
    }
}
