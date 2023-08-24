package com.yekaa.common.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    // global error response template
    private LocalDateTime timestamp;
    private String errorCode;
    private String errorMessage;
    private List<String> errorDetails;
    private String path;

}
