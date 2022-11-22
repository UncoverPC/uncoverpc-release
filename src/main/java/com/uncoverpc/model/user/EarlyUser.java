package com.uncoverpc.model.user;

public class EarlyUser {
	private String email;
	private String referral;
	private String verificationCode;
	
	public EarlyUser(String email, String referral) {
		super();
		this.email = email;
		this.referral = referral;
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
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setReferral(String referral) {
		this.referral = referral;
	}
	
	public void setVerificationCode(String code) {
		this.verificationCode = code;
	}
	

}
