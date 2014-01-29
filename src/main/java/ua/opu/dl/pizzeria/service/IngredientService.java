package ua.opu.dl.pizzeria.service;

import java.util.List;

import ua.opu.dl.pizzeria.model.Ingredient;

public interface IngredientService {
	void addIngredient(Ingredient ingredient);

	void updateIngredient(Ingredient ingredient);

	void deleteIngredient(Ingredient ingredient);

	Ingredient loadById(long id);

	List<Ingredient> loadByPizza(long pizzaId);
}
