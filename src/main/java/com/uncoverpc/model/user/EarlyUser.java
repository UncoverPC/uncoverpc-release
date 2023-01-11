package com.uncoverpc.model.user;

import org.springframework.data.mongodb.core.mapping.Document;
@Document("earlyusers")
public class EarlyUser {

	private String name;
	private String email;
	private String verificationCode;
	private Boolean enabled = false;
	
	public EarlyUser() {
		
	}
	
	public EarlyUser(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return this.email;
	}

	public String getVerificationCode() {
		return this.verificationCode;
	}
	public Boolean getEnabled() {
		return this.enabled;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public void setVerificationCode(String code) {
		this.verificationCode = code;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

}
