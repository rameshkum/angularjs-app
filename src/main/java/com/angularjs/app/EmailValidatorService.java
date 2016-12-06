package com.angularjs.app;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * http://localhost:8082/angularjs-app/#/email
 * @author srinivas.yerra
 * EmailValidatorService webservice validates the email.
 */
@Path("emailService")
public class EmailValidatorService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmailData> validateEmail(@QueryParam("email") String email) {
		EmailValidator _emailValidator = EmailValidator.getInstance();
		boolean _valid = _emailValidator.isValid(email);

		List<EmailData> _result = new ArrayList<>();
		EmailData _data = new EmailData();
		if (_valid) {
			String[] _tokens = email.split("@");
			_data.setValid(_valid);
			_data.setEmail(email);
			_data.setUsername(_tokens[0]);
			_data.setHostname(_tokens[1]);
		} else {
			_data.setValid(_valid);
		}
		_result.add(_data);
		return _result;
	}

}
