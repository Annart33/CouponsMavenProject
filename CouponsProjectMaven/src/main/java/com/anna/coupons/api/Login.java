package com.anna.coupons.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.anna.coupons.beans.UserLoginData;
import com.anna.coupons.dao.CompanyDao;
import com.anna.coupons.dao.CustomerDao;
import com.anna.coupons.enums.UserType;
import com.anna.coupons.exceptions.ApplicationException;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Login {

	@Path("/login")
	@POST
	public void login(UserLoginData loginData, @Context HttpServletResponse response) throws ApplicationException {
		String email = loginData.getEmail();
		String password = loginData.getPassword();
		UserType type = loginData.getUserType();
		// ask if session is needed
		Long id = null;

		if (type == UserType.COMPANY) {
			CompanyDao companyDao = new CompanyDao();
			id = companyDao.login(email, password);
		} else if (type == UserType.CUSTOMER) {
			CustomerDao customerDao = new CustomerDao();
			id = customerDao.login(email, password);
		}

		Cookie cookie = new Cookie("login", Long.toString(id));
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
