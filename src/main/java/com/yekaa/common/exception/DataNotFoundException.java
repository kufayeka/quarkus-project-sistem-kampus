package com.yekaa.common.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// this is custom not found exception
@Getter
@Setter
public class DataNotFoundException extends RuntimeException {
    private final List<String> errorMessages;

    public DataNotFoundException(List<String> msg) {
        this.errorMessages  = msg;
    }
}
