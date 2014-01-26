package ua.opu.dl.pizzeria.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);

    private int id;
    private Status status;
    private Date starttime;
    private Date endtime;
    private double price;
    private List<Product> products;
    private String phone;

    public Order() {
    }

    public Order(List<Product> products, Status status, Date starttime,
                 Date endtime, double price,
                 String phone) {
        this.products = products;
        this.status = status;
        this.starttime = starttime;
        this.endtime = endtime;
        this.price = price;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pizza> getPizzas() {

        List<Pizza> pizzas = new ArrayList();

        for (Product product : products) {
            if (product instanceof Pizza) {
                pizzas.add((Pizza) product);
            }
        }

        return pizzas;
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

    public List<Additional> getAdditions() {

        List<Additional> additions = new ArrayList();

        for (Product product : products) {
            if (product instanceof Additional) {
                additions.add((Additional) product);
            }
        }

        return additions;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {

        if (products.contains(product)) {
            int pos = products.indexOf(product);
            Product p = products.get(pos);
            p.incrementQuantity();
            products.set(pos, p);
        } else {
            products.add(product);
        }

        setPrice(getPrice() + product.getPrice());
    }

    public void removeProduct(Integer id) {

        for (Product product : products) {
            if (product.getId().equals(id)) {
                products.remove(product);
                setPrice(getPrice() - product.getPrice() * product.getQuantity());
                break;
            }
        }
    }

    public void changeProductQuantity(Integer id, Integer quantity) {

        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setQuantity(quantity);
                updatePrice();
                break;
            }
        }
    }

    public void updatePrice() {

        double totalPrice = 0.0;

        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }

        setPrice(totalPrice);
    }
}
