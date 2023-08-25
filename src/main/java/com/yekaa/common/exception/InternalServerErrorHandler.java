package com.yekaa.common.exception;

import com.yekaa.common.response.ErrorResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Provider
@ApplicationScoped
public class InternalServerErrorHandler implements ExceptionMapper<InternalServerErrorException> {

    @Context
    private UriInfo uriInfo;
    @Override
    public Response toResponse(InternalServerErrorException ex) {
        // check if ex is contains more than one error
        List<String> errorList = new ArrayList<>();

        if (ex instanceof List) {
            List<InternalServerErrorException> exceptionList = (List<InternalServerErrorException>) ex;
            for (InternalServerErrorException exception : exceptionList) {
                errorList.add(exception.toString());
            }
        } else {
            errorList.add(ex.getLocalizedMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("INTERNAL_SERVER_ERROR");
        errorResponse.setErrorMessage("Internal Server error.");
        errorResponse.setErrorDetails(errorList);
        errorResponse.setPath(uriInfo.getPath());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorResponse)
                .build();
    }
}
