package com.uncoverpc.model.user;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.uncoverpc.model.user.Roles.Role;

@Document("users")
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String email;

    private Set<Role> roles = new HashSet<>();

    private String password;

    private String confirmPassword;

    private String username;

    private Long registerTimestamp;

    private String firstName;

    private String lastName;

    private String verificationCode;

    private boolean enabled;

	//local, google, etc...
	private Provider provider = Provider.LOCAL;

    public User(String id, String email, Set<Role> roles, String password, String confirmPassword, String username,
            String firstName, String lastName) {
        super();
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.username = username;
        setRegisterTimestamp();
        this.firstName = firstName;
        this.lastName = lastName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getRegisterTimestamp() {
        return registerTimestamp;
    }

    public void setRegisterTimestamp() {
        LocalDate localDate = LocalDate.now();
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        long timeInMillis = instant.toEpochMilli();
        instant = localDate.atTime(LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant();
        this.registerTimestamp = instant.toEpochMilli();
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role user) {
        roles.add(user);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password
                + ", confirmPassword=" + confirmPassword + ", username=" + username + ", firstName=" + firstName
                + ", lastName=" + lastName + ", verificationCode=" + verificationCode + ", enabled=" + enabled + "]";
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}
