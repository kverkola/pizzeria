package ua.opu.dl.pizzeria.model;

import java.util.Map;

public class Pizza extends Product {

	private Map<Ingredient, Integer> map;
	private int orderId;
	private String logo;
	private String description;

	public Pizza() {
	}

	public Pizza(String name, Map<Ingredient, Integer> map, String logo,
			String description, Integer id, double price) {

        super(id, name, 1, price);

        this.map = map;
		this.logo = logo;
		this.description = description;

        countTotalPrice();
	}

	public Map<Ingredient, Integer> getMap() {
		return map;
	}

	public void setMap(Map<Ingredient, Integer> map) {
		this.map = map;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

		if (Double.compare(pizza.getPrice(), getPrice()) != 0)
			return false;
		if (getId() != null ? !getId().equals(pizza.getId())
				: pizza.getId() != null)
			return false;
		if (map != null ? !map.equals(pizza.map) : pizza.map != null)
			return false;
		if (getName() != null ? !getName().equals(pizza.getName()) : pizza
				.getName() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (map != null ? map.hashCode() : 0);
		temp = Double.doubleToLongBits(getPrice());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
}
