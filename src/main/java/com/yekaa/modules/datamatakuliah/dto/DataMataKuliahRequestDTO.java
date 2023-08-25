package com.yekaa.modules.datamatakuliah.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record DataMataKuliahRequestDTO(
        @NotBlank(message = "Kode mata kuliah tidak boleh kosong.")
        String kode,
        @NotBlank(message = "Nama mata kuliah tidak boleh kosong.")
        String nama,
        @NotBlank(message = "Sks mata kuliah tidak boleh kosong.")
        int sks
) {
}
