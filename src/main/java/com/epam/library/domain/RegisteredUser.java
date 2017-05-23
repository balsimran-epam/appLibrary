package com.epam.library.domain;

public class RegisteredUser extends User {

	private String email;
	private String streetAddress;
	private String localityAddress;
	private String language;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getLocalityAddress() {
		return localityAddress;
	}

	public void setLocalityAddress(String localityAddress) {
		this.localityAddress = localityAddress;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
