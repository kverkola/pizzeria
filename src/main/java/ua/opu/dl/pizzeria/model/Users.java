package ua.opu.dl.pizzeria.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import ua.opu.dl.pizzeria.util.constraints.Matches;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Matches(fields={"password"}, verifyFields={"confirmPassword"})
public class Users implements GrantedAuthority {

    @NotEmpty
    @Size(min = 3, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String lastName;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String login;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;
    private String confirmPassword;

    @Valid
    private Customer customer;
    private UserRole role;
    private long id;

    public Users() {

    }

    public Users(String firstName, String lastName, String login,
                String password, Customer customer, UserRole role, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.customer = customer;
        this.role = role;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

	@Override
	public String getAuthority() {
		
		return role.toString();
	}
}
