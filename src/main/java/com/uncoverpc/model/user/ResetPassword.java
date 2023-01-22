package com.uncoverpc.model.user;

public class ResetPassword {
	private String email;
	private String password;
	private String confirmPassword;
	
	public ResetPassword() {
		
	}
	
	public ResetPassword(String email, String password, String confirmPassword) {
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public String getConfirmPassword() {
		return this.confirmPassword;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirmPassword(String password) {
		this.confirmPassword = password;
	}
}

