package ua.opu.dl.pizzeria.dao.impl;

import ua.opu.dl.pizzeria.dao.PizzaDao;
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

    @Override
    public List<Pizza> loadAll() {

        List<Pizza> pizzaList = new ArrayList();

        Map<Ingredient, Integer> ingredientMap = new HashMap();
        ingredientMap.put(new Ingredient("Ham", 100, 30), 1);
        ingredientMap.put(new Ingredient("Vegetables", 80, 10), 1);
        ingredientMap.put(new Ingredient("Cheese", 90, 20), 1);
        ingredientMap.put(new Ingredient("Sauce", 60, 15), 1);
        ingredientMap.put(new Ingredient("Crust", 300, 30), 1);

        String[] imgs = {"chikenita_middle.png", "img_2.png", "pizza_middle.png", "tanu_mini.png"};
        String[] descrips = {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit.",
                "Aenean commodo ligula eget dolor. Aenean massa.",
                "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
                "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."
        };

        for (int i = 0; i < 4; i++) {
            pizzaList.add(new Pizza("TestPizza " + i, ingredientMap, 25, imgs[i], descrips[i]));
        }
        return pizzaList;
    }
}
