package com.yekaa.common.exception;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.java.Log;

@Log
@Provider
@ApplicationScoped
public class JsonParseExceptionHandler implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException ex) {
        return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
    }
}
