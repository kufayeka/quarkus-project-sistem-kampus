package com.yekaa.modules.datamahasiswa.dto;

public record DataMahasiswaResponseDTO(
        Long id,
        String nama_mahasiswa,
        String nrp_mahasiswa,
        String jurusan_mahasiswa
) {
}
