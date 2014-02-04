package ua.opu.dl.pizzeria.model;

import org.hibernate.validator.constraints.NotEmpty;
import ua.opu.dl.pizzeria.util.constraints.Matches;

import javax.validation.constraints.Size;

@Matches(fields={"password"}, verifyFields={"confirmPassword"})
public class User {

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

    private Status warker;
    private long id;
    public User() {

    }

    public User(String firstName, String lastName, String login,
                String password, Status warker,long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.warker = warker;
        this.id=id;
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

    public Status getWarker() {
        return warker;
    }

    public void setWarker(Status warker) {
        this.warker = warker;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
