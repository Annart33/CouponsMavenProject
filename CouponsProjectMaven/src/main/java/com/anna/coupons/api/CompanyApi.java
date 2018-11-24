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

import com.anna.coupons.beans.Company;
import com.anna.coupons.controller.CompanyController;
import com.anna.coupons.exceptions.ApplicationException;

@Path("/companies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyApi {

	private CompanyController companyController;

	public CompanyApi() {
		companyController = new CompanyController();
	}

	@POST
	public void createCompany(Company company) throws ApplicationException {
		companyController.createCompany(company);
	}

	@DELETE
	@Path("/{companyId}")
	public void deleteCompany(@PathParam("companyId") long companyId) throws ApplicationException {
		companyController.deleteCompany(companyId);
	}

	@PUT
	public void updateCompany(Company company) throws ApplicationException {
		companyController.updateCompany(company);
	}

	@GET
	public List<Company> getAllCompanies() throws ApplicationException {
		return companyController.getAllCompanies();
	}

	@GET
	@Path("/{companyId}")
	public Company getCompany(@PathParam("companyId") long companyId) throws ApplicationException {
		return companyController.getCompany(companyId);
	}

}
