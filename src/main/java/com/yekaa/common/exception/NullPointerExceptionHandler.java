package com.yekaa.common.exception;

import com.yekaa.common.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Provider
public class NullPointerExceptionHandler implements ExceptionMapper<NullPointerException> {

    @Context
    private UriInfo uriInfo;
    @Override
    public Response toResponse(NullPointerException ex) {
        // check if ex is contains more than one error
        List<String> errorList = new ArrayList<>();

        if (ex instanceof List) {
            List<NullPointerException> exceptionList = (List<NullPointerException>) ex;
            for (NullPointerException exception : exceptionList) {
                errorList.add(exception.getLocalizedMessage());
            }
        } else {
            errorList.add(ex.getLocalizedMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("INTERNAL_SERVER_ERROR");
        errorResponse.setErrorMessage("An error occurred while processing your request.");
        errorResponse.setErrorDetails(errorList);
        errorResponse.setPath(uriInfo.getPath());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
