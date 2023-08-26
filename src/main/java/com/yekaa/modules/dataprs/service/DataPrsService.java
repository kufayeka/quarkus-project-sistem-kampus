package com.yekaa.modules.dataprs.service;

import com.yekaa.modules.dataprs.dto.DataPrsRequestDTO;
import com.yekaa.modules.dataprs.dto.DataPrsResponseDTO;

import java.util.List;
import java.util.Optional;

// service interface
public interface DataPrsService {
    List<DataPrsResponseDTO> getAllDataPrs();
    Optional<DataPrsResponseDTO> getDataPrsById(Long id) throws Exception;
    void createDataPrs(DataPrsRequestDTO dto);
    void deleteDataPrsById(Long id);
}
