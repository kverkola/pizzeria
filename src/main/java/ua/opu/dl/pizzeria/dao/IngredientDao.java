package ua.opu.dl.pizzeria.dao;

import java.util.List;

import ua.opu.dl.pizzeria.model.Ingredient;

public interface IngredientDao {
	void addIngredient(Ingredient ingredient);

	void updateIngredient(Ingredient ingredient);

	void deleteIngredient(Ingredient ingredient);

	Ingredient loadById(long id);

	List<Ingredient> loadIngredientsByPizza(long pizzaId);
}
