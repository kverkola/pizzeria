package ua.opu.dl.pizzeria.model;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class GuestUser {

    @NotEmpty
    @Size(min = 3, max = 25)
    private String name;

    @NotEmpty
    @Size(min = 5, max = 400)
    private String address;

    @NotEmpty
    @Size(min = 10, max = 12)
    private String phone;

    public GuestUser() {

    }

    public GuestUser(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
