package ua.opu.dl.pizzeria.dao;

import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.model.Pizza;

import javax.sql.DataSource;

public class IngredientDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Ingredient ingredient) {

    }

    public void update(Ingredient ingredient) {

    }

    public void delete(Pizza ingredient) {

    }

    /**
     * Currently this method don't return real object from db
     * @param id
     * @return
     */
    public Ingredient loadById(Integer id) {

        return new Ingredient("Test", 100, 100);
    }
}
