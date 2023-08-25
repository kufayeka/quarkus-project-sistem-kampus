package com.yekaa.common.exception;

import com.yekaa.common.response.ErrorResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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
@ApplicationScoped
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Context
    private UriInfo uriInfo;
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        // check if ex is contains more than one error
        List<String> validationErrors = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : constraintViolations) {
            validationErrors.add(violation.getMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("CONSTRAINT_VIOLATION_ERROR");
        errorResponse.setErrorMessage("Constraint violated.");
        errorResponse.setErrorDetails(validationErrors);
        errorResponse.setPath(uriInfo.getPath());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }
}
