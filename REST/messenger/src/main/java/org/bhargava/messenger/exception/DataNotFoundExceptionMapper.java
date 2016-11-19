package org.bhargava.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.bhargava.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage em = new ErrorMessage();
		em.setErrorMessage(ex.getMessage());
		return Response.status(Status.NOT_FOUND)
				.entity(em)
				.build();
	}

}
