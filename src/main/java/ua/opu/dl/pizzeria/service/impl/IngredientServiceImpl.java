package ua.opu.dl.pizzeria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.opu.dl.pizzeria.dao.impl.IngredientDaoImpl;
import ua.opu.dl.pizzeria.model.Ingredient;
import ua.opu.dl.pizzeria.service.IngredientService;

public class IngredientServiceImpl implements IngredientService {
	@Autowired
	private IngredientDaoImpl ingredientDao;

	@Override
	public void addIngredient(Ingredient ingredient) {
		ingredientDao.addIngredient(ingredient);

	}

	@Override
	public void updateIngredient(Ingredient ingredient) {
		ingredientDao.updateIngredient(ingredient);

	}

	@Override
	public void deleteIngredient(Ingredient ingredient) {
		ingredientDao.deleteIngredient(ingredient);

	}

	@Override
	public Ingredient loadById(Integer id) {

		return ingredientDao.loadById(id);
	}

	@Override
	public List<Ingredient> loadByPizza(Integer pizzaId) {

		return ingredientDao.loadByPizza(pizzaId);
	}

}
