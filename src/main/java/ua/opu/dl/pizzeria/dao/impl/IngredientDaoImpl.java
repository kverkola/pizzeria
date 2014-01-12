package ua.opu.dl.pizzeria.dao.impl;

import java.util.List;

import ua.opu.dl.pizzeria.dao.IngredientDao;
import ua.opu.dl.pizzeria.model.Ingredient;

public class IngredientDaoImpl implements IngredientDao {

	@Override
	public void addIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteIngredient(Ingredient ingredient) {
		

	}

	@Override
	public Ingredient loadById(Integer id) {

		return new Ingredient("cheese",50,5);
	}

	@Override
	public List<Ingredient> loadIngredientsByPizza(Integer pizzaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
