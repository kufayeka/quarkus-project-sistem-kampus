package com.yekaa.modules.datamahasiswa.dto;

import com.yekaa.modules.dataprs.entity.DataPrs;

import java.util.Set;

public record DataMahasiswaResponseDTO(
        Long id,
        String nama_mahasiswa,
        String nrp_mahasiswa,
        String jurusan_mahasiswa,
        Set<DataPrs> data_registrasi
) {
}
