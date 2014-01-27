package ua.opu.dl.pizzeria.model;

public class Product {

    private Integer productId;
    private Integer id;
    private String name;
    private int quantity;
    private double price;

    public Product() {

    }

    public Product(Integer id, String name, int quantity, double price) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void incrementQuantity() {
        setQuantity(getQuantity() + 1);
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
