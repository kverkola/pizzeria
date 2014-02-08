package ua.opu.dl.pizzeria.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private static final Logger LOG = LoggerFactory.getLogger(Order.class);
    private static Integer productIdCounter = 0;

    private long id;
    private Status status;
    private String starttime;
    private String endtime;
    private double price;
    private String phone;
    private String address;
    private String nameCustomer;

    private List<Product> products;

    public Order() {
    	status = Status.PRE_ORDER;
    }

    public Order(List<Product> products,
                 Date starttime, Date endtime,
                 double price, String phone,String nameCustomer,String address) {

        this.products = products;
        this.status = Status.PRE_ORDER;
        this.starttime = starttime.toString();
        this.endtime = starttime.toString();;
        this.price = price;
        this.phone = phone;
        this.nameCustomer=nameCustomer;
        this.address=address;
    }

    public static Integer getProductIdCounter() {
        return productIdCounter;
    }

    public static void setProductIdCounter(Integer productIdCounter) {
        Order.productIdCounter = productIdCounter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public <T extends Product> List<T> getProducts(Class<T> clazz) {

        List<T> productsList = new ArrayList();

        for (Product p : products) {
            if (clazz.isAssignableFrom(p.getClass())) {
                productsList.add(clazz.cast(p));
            }
        }

        return productsList;
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

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public void addProduct(Product product) {

        if (products.contains(product)) {
            int pos = products.indexOf(product);
            products.get(pos).incrementQuantity();
        } else {
            product.setProductId(productIdCounter++);
            products.add(product);
        }

        setPrice(getPrice() + product.getPrice());
    }

    public void removeProduct(long productId) {

        for (Product product : products) {
            if (product.getProductId() == productId) {
                products.remove(product);
                setPrice(getPrice() - product.getPrice() * product.getQuantity());
                break;
            }
        }
    }

    public void changeProductQuantity(long productId, Integer quantity) {

        for (Product product : products) {
            if (product.getProductId() == productId) {
                product.setQuantity(quantity);
                updatePrice();
                break;
            }
        }
    }

    private void updatePrice() {

        double totalPrice = 0.0;

        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }

        setPrice(totalPrice);
    }
}
