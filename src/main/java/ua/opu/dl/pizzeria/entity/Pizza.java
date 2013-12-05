package ua.opu.dl.pizzeria.entity;

import java.util.Map;

public class Pizza {
	private String name;
	private Map<Ingredient, Integer> map;
	double price;

	public Pizza(String name, Map<Ingredient, Integer> map, double price) {
		this.name = name;
		this.map = map;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Ingredient, Integer> getMap() {
		return map;
	}

	public void setMap(Map<Ingredient, Integer> map) {
		this.map = map;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
