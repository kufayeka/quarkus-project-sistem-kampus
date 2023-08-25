package com.yekaa.modules.datamatakuliah.service;

import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaRequestDTO;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaResponseDTO;
import com.yekaa.modules.datamatakuliah.dto.DataMataKuliahRequestDTO;
import com.yekaa.modules.datamatakuliah.dto.DataMataKuliahResponseDTO;

import java.util.List;
import java.util.Optional;

// service interface
public interface DataMataKuliahService {
    List<DataMataKuliahResponseDTO> getAllDataMataKuliah();
    Optional<DataMataKuliahResponseDTO> getDataMataKuliahById(Long id);
    void createDataMataKuliah(DataMataKuliahRequestDTO dto);
    void deleteDataMataKuliahById(Long id);
}
