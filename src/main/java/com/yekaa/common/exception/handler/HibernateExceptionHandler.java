package com.yekaa.common.exception.handler;

import com.yekaa.common.exception.response.ErrorResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hibernate.HibernateException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Provider
@ApplicationScoped
public class HibernateExceptionHandler implements ExceptionMapper<HibernateException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(HibernateException ex) {
        List<String> errorList = new ArrayList<>();

        // Handle different types of Hibernate exceptions
        if (ex instanceof org.hibernate.exception.ConstraintViolationException) {
            errorList.add("Constraint violation error: " + ex.getLocalizedMessage());
        } else if (ex instanceof org.hibernate.PropertyValueException) {
            errorList.add("Property value error: " + ex.getLocalizedMessage());
        } else if (ex instanceof org.hibernate.JDBCException) {
            errorList.add("JDBC error: " + ex.getLocalizedMessage());
        } else {
            // Handle other types of Hibernate exceptions
            errorList.add("Hibernate error: " + ex.getLocalizedMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("HIBERNATE_ERROR");
        errorResponse.setErrorMessage("An error occurred while processing your request.");
        errorResponse.setErrorDetails(errorList);
        errorResponse.setPath(uriInfo.getPath());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }
}
