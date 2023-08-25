package com.yekaa.modules.datamatakuliah.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataMataKuliahRequestDTO(
        @NotBlank(message = "Kode mata kuliah tidak boleh kosong.")
        String kode_mata_kuliah,
        @NotBlank(message = "Nama mata kuliah tidak boleh kosong.")
        String nama,
        @NotNull(message = "Sks mata kuliah tidak boleh kosong.")
        int sks
) {
}
