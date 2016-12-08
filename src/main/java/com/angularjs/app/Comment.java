package com.angularjs.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author srinivas.yerra
 * 
 * comment class is used to transform xml to JSON
 *
 */

@XmlRootElement
public class Comment {

	/**
	 *PostId
	 */
	@XmlElement(name = "postId")
	private String postId;

	/**
	 *Message ID
	 */
	@XmlElement(name = "id")
	private String id;

	/**
	 *Commenter name
	 */
	@XmlElement(name = "name")
	private String name;

	/**
	 *Commenter email
	 */
	@XmlElement(name = "email")
	private String email;

	/**
	 * Comment
	 */
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
