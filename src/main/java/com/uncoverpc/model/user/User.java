package com.uncoverpc.model.user;

import java.util.UUID;

public class User {

	private String id = UUID.randomUUID().toString();

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName
				+ ", password=" + password + "]";
	}
	
}
