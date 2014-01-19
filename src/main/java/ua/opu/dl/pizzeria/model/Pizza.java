package ua.opu.dl.pizzeria.model;

import java.util.Map;

public class Pizza {
	private Integer id;
	private String name;
	private Map<Ingredient, Integer> map;
	private double price;
	private String logo;
	private String description;

	public Pizza(String name, Map<Ingredient, Integer> map, Integer id) {
		this.name = name;
		this.map = map;
		this.id = id;
		countTotalPrice();
	}

	public Pizza(String name, Map<Ingredient, Integer> map, String logo,
			String description, Integer id) {// не забыть удалить
												// id из
												// конструктора
		this.name = name;
		this.map = map;
		this.logo = logo;
		this.description = description;
		this.id = id;
		countTotalPrice();
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void countTotalPrice() {
		double price = 0;
		Integer key;
		Map<Ingredient, Integer> ingrMap = getMap();
		for (Ingredient ingr : ingrMap.keySet()) {
			key = ingrMap.get(ingr);
			price = price + ingr.getPrice() * key;
		}
		setPrice(price);

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Pizza pizza = (Pizza) o;

		if (Double.compare(pizza.price, price) != 0)
			return false;
		if (description != null ? !description.equals(pizza.description)
				: pizza.description != null)
			return false;
		if (logo != null ? !logo.equals(pizza.logo) : pizza.logo != null)
			return false;
		if (name != null ? !name.equals(pizza.name) : pizza.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = name != null ? name.hashCode() : 0;
		temp = Double.doubleToLongBits(price);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (logo != null ? logo.hashCode() : 0);
		result = 31 * result
				+ (description != null ? description.hashCode() : 0);
		return result;
	}
}
