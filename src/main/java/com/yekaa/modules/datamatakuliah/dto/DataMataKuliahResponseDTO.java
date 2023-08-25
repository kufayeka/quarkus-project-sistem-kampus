package com.yekaa.modules.datamatakuliah.dto;

import jakarta.validation.constraints.NotBlank;

public record DataMataKuliahResponseDTO(
        Long id,
        String kode_mata_kuliah,
        String nama,
        int sks
) {
}
