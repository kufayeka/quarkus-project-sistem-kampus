package com.yekaa.modules.datamatakuliah.dto;

import jakarta.validation.constraints.NotBlank;

public record DataMataKuliahResponseDTO(
        Long id,
        String kode,
        String nama,
        int sks
) {
}
