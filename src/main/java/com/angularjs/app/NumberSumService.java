package com.angularjs.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * http://localhost:8082/angularjs-app/rs/sum?number=3303
 * 
 * @author srinivas.yerra
 *
 */
@Path("sum")
public class NumberSumService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int sum(@QueryParam("number") int number) {
		int _result = total(number);
		return _result;
	}

	/**
	 * recursively sum the numbers
	 * 
	 * @param number
	 * @return
	 */
	private int total(int number) {
		int sum = number % 10;
		if (number / 10 < 10) {
			return sum + number / 10;
		} else {
			return sum + total(number / 10);
		}
	}

}
