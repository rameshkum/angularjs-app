package com.angularjs.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author srinivas.yerra
 *
 */
@XmlRootElement
public class EmailData {

	@XmlElement(name = "valid")
	private boolean valid;

	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "username")
	private String username;
	
	@XmlElement(name = "hostname")
	private String hostname;

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
