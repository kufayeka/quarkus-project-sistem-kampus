package com.yekaa.common.exception.handler;

import com.yekaa.common.exception.DataNotFoundException;
import com.yekaa.common.exception.response.ErrorResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;
import java.util.List;

@Provider
@ApplicationScoped
public class DataNotFoundExceptionHandler implements ExceptionMapper<DataNotFoundException> {

    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(DataNotFoundException ex) {
        List<String> errorMessages = ex.getErrorMessages();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setErrorCode("NOT_FOUND");
        errorResponse.setErrorMessage("Resource not found");
        errorResponse.setErrorDetails(errorMessages);
        errorResponse.setPath(uriInfo.getPath());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}


