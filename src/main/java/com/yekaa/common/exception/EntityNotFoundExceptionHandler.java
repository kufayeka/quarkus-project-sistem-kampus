package com.yekaa.common.exception;

import com.yekaa.common.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Provider
public class EntityNotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException> {

    @Context
    private UriInfo uriInfo;
    @Override
    public Response toResponse(EntityNotFoundException ex) {
        // check if ex is contains more than one errors
        List<String> errorList = new ArrayList<>();

        if (ex instanceof List) {
            List<EntityNotFoundException> exceptionList = (List<EntityNotFoundException>) ex;
            for (EntityNotFoundException exception : exceptionList) {
                errorList.add(exception.getLocalizedMessage());
            }
        } else {
            errorList.add(ex.getLocalizedMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("ENTITY_NOT_FOUND");
        errorResponse.setErrorMessage("Entity not found");
        errorResponse.setErrorDetails(errorList);
        errorResponse.setPath(uriInfo.getPath());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}


