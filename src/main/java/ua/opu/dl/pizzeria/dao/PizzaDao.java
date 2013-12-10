package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Pizza pizza) {

    }

    public void update(Pizza pizza) {

    }

    public void delete(Pizza pizza) {

    }

    /**
     * Currently this method don't return real object from db
     * @param id
     * @return
     */
    public Pizza loadById(Integer id) {

        Map<Ingredient, Integer> ingredientMap = new HashMap<>();
        ingredientMap.put(new Ingredient("Ham", 100, 30), 1);
        ingredientMap.put(new Ingredient("Vegetables", 80, 10), 1);
        ingredientMap.put(new Ingredient("Cheese", 90, 20), 1);
        ingredientMap.put(new Ingredient("Sauce", 60, 15), 1);
        ingredientMap.put(new Ingredient("Crust", 300, 30), 1);

        return new Pizza("TestPizza", ingredientMap, 25);
    }

    public List<Pizza> loadByOrder(Integer orderId) {

        List<Pizza> pizzaList = new ArrayList<>();
        return pizzaList;
    }
}
