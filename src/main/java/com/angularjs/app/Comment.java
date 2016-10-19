package com.angularjs.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author srinivas.yerra
 *
 */
@XmlRootElement
public class Comment {

	@XmlElement(name = "postId")
	private String postId;

	@XmlElement(name = "id")
	private String id;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "email")
	private String email;

	@XmlElement(name = "body")
	private String body;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
