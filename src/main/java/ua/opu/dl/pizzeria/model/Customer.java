package ua.opu.dl.pizzeria.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Customer {

    @NotEmpty
    @Size(min = 3, max = 25)
    private String name;

    @NotEmpty
    @Size(min = 5, max = 400)
    private String address;

    @NotEmpty
    @Size(min = 7, max = 12)
    private String phone;
    private Long id;
    public Customer() {
    id=(long) 0;
    }

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        id=(long) 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
