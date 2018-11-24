package com.anna.coupons.exceptions;

import javax.ws.rs.core.Response;


public class ExceptionHandler {

	//@ExceptionHandler(Exception.class)
	public Response toResponse(Throwable exception) {

		if (exception instanceof ApplicationException) {
			ApplicationException applicationException = (ApplicationException) exception;

			return Response.status(applicationException.getErrorType().getErrorNumber()).build();

		}

		return Response.status(601).build();
	}

}
