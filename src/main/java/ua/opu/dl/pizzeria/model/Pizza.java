package ua.opu.dl.pizzeria.model;

import java.util.Map;

public class Pizza extends Product {

	private Map<Ingredient, Integer> map;
	private long orderId;
	private String logo;
	private String description;
	private String cook;

	public Pizza() {
	}

    public Pizza(String name, String logo,
                 String description, long id, double price,String cook) {

        super(id, name, 1, price);

        this.logo = logo;
        this.cook = cook;
        this.description = description;
    }

	public Pizza(String name, Map<Ingredient, Integer> map, String logo,
			String description, long id, double price) {

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
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

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pizza))
			return false;
		Pizza other = (Pizza) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	
}
