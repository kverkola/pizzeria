package ua.opu.dl.pizzeria.model;

import java.util.Map;

public class Pizza {
	private String name;
	private Map<Ingredient, Integer> map;
	private double price;
    private String logo;
    private String description;

    public Pizza(String name, Map<Ingredient, Integer> map, double price) {
        this.name = name;
        this.map = map;
        this.price = price;
    }

    public Pizza(String name, Map<Ingredient,
                 Integer> map, double price,
                 String logo, String description) {
        this.name = name;
        this.map = map;
        this.price = price;
        this.logo = logo;
        this.description = description;
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
}
