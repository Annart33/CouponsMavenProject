package com.anna.coupons.api;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.anna.coupons.beans.Coupon;
import com.anna.coupons.controller.CouponController;
import com.anna.coupons.enums.CouponType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.CookieUtils;

@Path("/coupons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CouponApi {

	private CouponController couponController;

	public CouponApi() {
		couponController = new CouponController();
	}

	@GET
	public List<Coupon> getAllCoupons() throws ApplicationException {

		return couponController.getAllCoupons();
	}

	@GET
	@Path("/{couponId}")
	public Coupon getCoupon(@PathParam("couponId") long couponId) throws ApplicationException {
		return couponController.getCoupon(couponId);
	}

	@DELETE
	@Path("/{couponId}")
	public void deleteCoupon(@PathParam("couponId") long couponId) throws ApplicationException {
		couponController.deleteCoupon(couponId);
	}

	@DELETE
	@Path("/{companyId}/byCompany")
	public void deleteCouponByCompany(@PathParam("companyId") long companyId) throws ApplicationException {
		couponController.deleteCouponByCompany(companyId);
	}

	@POST
	public void createCoupon(Coupon coupon) throws ApplicationException, SQLException {
		couponController.createCoupon(coupon);
	}

	@PUT
	public void updateCoupon(Coupon coupon) throws ApplicationException {
		couponController.updateCoupon(coupon);
	}

	@GET
	@Path("/{couponType}/byCouponType")
	public List<Coupon> getCouponByCouponType(@PathParam("couponType") CouponType couponType)
			throws ApplicationException {
		return couponController.getCouponByCouponType(couponType);
	}

	@GET
	@Path("/couponPrice")
	public List<Coupon> getCouponsUpToPrice(@QueryParam("couponPrice") int couponPrice) throws ApplicationException {
		return couponController.getCouponsUpToPrice(couponPrice);
	}

	@GET
	@Path("/byCouponEndDate")
	public List<Coupon> getCouponsUpToDate(@QueryParam("couponEndDate") String couponEndDate)
			throws ApplicationException {
		return couponController.getCouponsUpToDate(couponEndDate);
	}

	@GET
	@Path("/byExpiration")
	public List<Coupon> getAllExpiredCoupons() throws ApplicationException {
		return couponController.getAllExpiredCoupons();
	}

	@GET
	@Path("/{customerId}/byCustomerId")
	public List<Coupon> getCouponByCustomerId(@PathParam("customerId") long customerId) throws ApplicationException {
		return couponController.getCouponByCustomerId(customerId);
	}

	@GET
	@Path("/{companyId}/byCompanyId")
	public List<Coupon> getCouponByCompanyId(@PathParam("companyId") long companyId) throws ApplicationException {
		return couponController.getCouponByCompanyId(companyId);
	}

	@DELETE
	@Path("/byExpiration")
	public void deleteExpiredCoupons() throws ApplicationException {
		couponController.deleteExpiredCoupons();
	}

	@DELETE
	@Path("/{couponId}/byCouponID")
	public void deleteCouponFromCouponCustomer(@PathParam("couponId") long couponId) throws ApplicationException {
		couponController.deleteCouponFromCouponCustomer(couponId);
	}

	@DELETE
	@Path("/byEndDate")
	public void deleteCouponsByEndDate(@QueryParam("couponEndDate") String couponEndDate) throws ApplicationException {
		couponController.deleteCouponsByEndDate(couponEndDate);
	}

	@POST
	@Path("/purchase/{couponId}")
	// @Context means - "give me the cookies of the request"
	public void purchase(@PathParam("couponId") long couponId, @Context HttpServletRequest request)
			throws ApplicationException {
		long customerId = 0;
		String StrCustomerId = CookieUtils.getCookieValue(request, "login");
		customerId = Long.parseLong(StrCustomerId);
		couponController.purchase(customerId, couponId);
	}

}
