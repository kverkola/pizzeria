package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaDaoImpl implements PizzaDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addPizza(Pizza pizza) {

    }

    @Override
    public void updatePizza(Pizza pizza) {

    }

    @Override
    public void deletePizza(Pizza pizza) {

    }

    /**
     * Currently this method don't return real object from db
     * @param id
     * @return
     */
    @Override
    public Pizza loadById(Integer id) {

        Map<Ingredient, Integer> ingredientMap = new HashMap();
        ingredientMap.put(new Ingredient("Ham", 100, 30), 1);
        ingredientMap.put(new Ingredient("Vegetables", 80, 10), 1);
        ingredientMap.put(new Ingredient("Cheese", 90, 20), 1);
        ingredientMap.put(new Ingredient("Sauce", 60, 15), 1);
        ingredientMap.put(new Ingredient("Crust", 300, 30), 1);

        return new Pizza("TestPizza", ingredientMap, 25);
    }

    @Override
    public List<Pizza> loadByOrder(Integer orderId) {

        List<Pizza> pizzaList = new ArrayList();
        return pizzaList;
    }
}
