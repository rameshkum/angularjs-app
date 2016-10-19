package com.angularjs.app;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * http://localhost:8082/angularjs-app [ Default URL routing is to post.html]
 * http://localhost:8082/angularjs-app/#/post
 * 
 * @author srinivas.yerra
 *
 */
@Path("post")
public class PostRestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getPost(@QueryParam("pageNumber") int pageNumber) {
		List<Post> _posts = PostCache.getPosts();
		int totalPages = PostCache.getTotalPages();
		if (pageNumber >= totalPages) {
			pageNumber = totalPages;
		} else if (pageNumber <= 1) {
			pageNumber = 1;
		}

		int pageSize = 20;
		int startingIndex = pageSize * (pageNumber - 1);
		if (startingIndex < 0) {
			startingIndex = 0;
		}
		int endingIndex = startingIndex + pageSize;
		if (endingIndex > _posts.size()) {
			endingIndex = _posts.size();
		}
		List<Post> _result = _posts.subList(startingIndex, endingIndex);
		return _result;
	}

	@GET
	@Path("/comment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@QueryParam("postId") int postId) {
		final ClientConfig clientConfig = new ClientConfig().register(new JacksonFeature());
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://jsonplaceholder.typicode.com/posts/" + postId + "/comments");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		List<Comment> _comments = invocationBuilder.get().readEntity(new GenericType<List<Comment>>() {
		});
		System.out.println("Comments are recieved ::::: "+ _comments.size());
		return _comments;
	}

}
