package ua.opu.dl.pizzeria.entity;

public class User {
	private String first_name;
	private String last_name;
	private String login;
	private String password;
	private Status warker;

	public User(String first_name, String last_name, String login,
			String password, Status warker) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.login = login;
		this.password = password;
		this.warker = warker;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public Status getWarker() {
		return warker;
	}

	public void setWarker(Status warker) {
		this.warker = warker;
	}

}
