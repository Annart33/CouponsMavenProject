package com.anna.coupons.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.anna.coupons.beans.Customer;
import com.anna.coupons.controller.CustomerController;
import com.anna.coupons.exceptions.ApplicationException;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerApi {

	private CustomerController customerController;

	public CustomerApi() {
		customerController = new CustomerController();
	}

	@POST
	public void createCustomer(Customer customer) throws ApplicationException {
		customerController.createCustomer(customer);

	}

	@GET
	@Path("/{customerId}")
	public Customer getCustomer(@PathParam("customerId") long customerId) throws ApplicationException {
		return customerController.getCustomer(customerId);
	}

	@DELETE
	@Path("/{customerId}")
	public void deleteCustomer(@PathParam("customerId") long customerId) throws ApplicationException {
		customerController.deleteCustomer(customerId);
	}

	@PUT
	public void updateCustomer(Customer customer) throws ApplicationException {
		customerController.updateCustomer(customer);
	}

	@GET
	public List<Customer> getAllCustomers() throws ApplicationException {
		return customerController.getAllCustomers();
	}



}
