package com.angularjs.app;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * Post cache retrieves the posts from the remote repository
 * and stores in the list
 * @author srinivas.yerra
 *
 */
public class PostCache {

	static final int PAGE_INDEX = 20;

	static int totalPages;

	static List<Post> posts;

	public static void initialize() {
	}

	public synchronized static List<Post> getPosts() {
		if (posts == null) {
			final ClientConfig clientConfig = new ClientConfig().register(new JacksonFeature());
			Client client = ClientBuilder.newClient(clientConfig);
			WebTarget webTarget = client.target("http://jsonplaceholder.typicode.com/posts");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			posts = invocationBuilder.get().readEntity(new GenericType<List<Post>>() {
			});
			if (posts.size() % PAGE_INDEX == 0) {
				totalPages = posts.size() / PAGE_INDEX;
			} else {
				totalPages = (posts.size() / PAGE_INDEX) + 1;
			}
		}
		return posts;
	}

	public static int getTotalPages() {
		return totalPages;
	}
}
