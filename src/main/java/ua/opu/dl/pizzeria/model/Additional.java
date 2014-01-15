package ua.opu.dl.pizzeria.model;

public class Additional {
	private String name;
	private double price;
	private String logo;
	public Additional(String name, double price,String logo) {
		this.name = name;
		this.price = price;
		this.logo= logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
