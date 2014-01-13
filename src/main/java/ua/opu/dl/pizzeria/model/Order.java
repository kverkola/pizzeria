package ua.opu.dl.pizzeria.model;

import java.util.Date;
import java.util.Map;

public class Order {

	private int id;
	private Map<Pizza, Integer> pizzas;// название и количество
	private Status status;
	private Date starttime;
	private Date endtime;
	private double price;
	private Map<Additional, Integer> additional;// название и количество
	private String phone;

    public Order() {
    }

    public Order(Map<Pizza, Integer> pizzas, Status status, Date starttime,
			Date endtime, double price, Map<Additional, Integer> additional,
			String phone) {
		this.pizzas = pizzas;
		this.status = status;
		this.starttime = starttime;
		this.endtime = endtime;
		this.price = price;
		this.additional = additional;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Pizza, Integer> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Pizza, Integer> pizzas) {
		this.pizzas = pizzas;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Map<Additional, Integer> getAdditional() {
		return additional;
	}

	public void setAdditional(Map<Additional, Integer> additional) {
		this.additional = additional;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
