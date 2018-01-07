/**
 * 
 */
package com.delphi.rest.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.JsonParseException;

/**
 * @author Athi
 *
 */
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
  @Override
  public Response toResponse(JsonParseException exception) {
    return Response.status(Response.Status.BAD_REQUEST).entity(exception.getClass().getSimpleName()).type("text/plain").build();
  }
}

