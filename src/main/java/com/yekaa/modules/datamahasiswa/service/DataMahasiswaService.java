package com.yekaa.modules.datamahasiswa.service;

import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaRequestDTO;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaResponseDTO;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// service interface
public interface DataMahasiswaService {
    List<DataMahasiswaResponseDTO> getAllDataMahasiswa();
    Optional<DataMahasiswaResponseDTO> getDataMahasiswaById(Long id);
    Optional<DataMahasiswaResponseDTO> getDataMahasiswaByNrp(String nrp);
    void createDataMahasiswa(DataMahasiswaRequestDTO dto);
    void deleteDataMahasiswaById(Long id);
}
