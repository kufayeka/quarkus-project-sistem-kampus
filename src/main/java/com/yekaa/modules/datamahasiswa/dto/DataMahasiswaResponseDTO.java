package com.yekaa.modules.datamahasiswa.dto;

import com.yekaa.modules.dataprs.entity.DataPrs;

import java.util.Set;

public record DataMahasiswaResponseDTO(
        Long id,
        String namaMahasiswa,
        String nrpMahasiswa,
        String jurusanMahasiswa,
        Set<DataPrs> dataRegistrasi
) {
}
