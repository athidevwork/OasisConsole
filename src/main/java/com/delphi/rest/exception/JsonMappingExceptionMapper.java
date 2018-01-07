/**
 * 
 */
package com.delphi.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author Athi
 *
 */
@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {
  @Override
  public Response toResponse(JsonMappingException exception) {
    return Response.status(Response.Status.BAD_REQUEST).entity(exception.getPathReference()).type("text/plain").build();
  }
}

