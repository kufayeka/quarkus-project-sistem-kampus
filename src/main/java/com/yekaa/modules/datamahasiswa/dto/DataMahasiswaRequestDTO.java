package com.yekaa.modules.datamahasiswa.dto;

import jakarta.validation.constraints.NotBlank;

public record DataMahasiswaRequestDTO(
        @NotBlank(message = "Nama tidak boleh kosong")
        String nama,
        @NotBlank(message = "Nrp tidak boleh kosong")
        String nrp,
        @NotBlank(message = "jurusan tidak boleh kosong")
        String jurusan
) {
}
