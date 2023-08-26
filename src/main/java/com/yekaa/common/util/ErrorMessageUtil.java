package com.yekaa.common.util;

import com.yekaa.common.exception.DataNotFoundException;
import com.yekaa.modules.dataprs.entity.DataPrs;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ErrorMessageUtil {
    public void throwFindByIdNotFoundError(String entityName, String id){
        List<String> error = new ArrayList<>();

        error.add(entityName + " with ID:" + id + " not found");
        throw new DataNotFoundException(error);
    }

    public void throwFindAllNotFoundError(String entityName){
        List<String> error = new ArrayList<>();

        error.add(entityName + " not found");
        throw new DataNotFoundException(error);
    }
}
