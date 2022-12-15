package com.uncoverpc.model.user;

import org.springframework.data.mongodb.core.mapping.Document;
@Document("early-users")
public class EarlyUser {

	private String name;
	private String email;
	private String referral;
	private String verificationCode;
	private Boolean enabled = false;
	
	public EarlyUser() {
		
	}
	
	public EarlyUser(String name, String email, String referral) {
		this.name = name;
		this.email = email;
		this.referral = referral;
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
	public String getReferral() {
		return this.referral;
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
	
	public void setReferral(String referral) {
		this.referral = referral;
	}
	
	public void setVerificationCode(String code) {
		this.verificationCode = code;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

}
