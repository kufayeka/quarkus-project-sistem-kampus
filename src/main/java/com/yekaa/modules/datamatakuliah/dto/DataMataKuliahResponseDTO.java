package com.yekaa.modules.datamatakuliah.dto;

import com.yekaa.modules.dataprs.entity.DataPrs;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record DataMataKuliahResponseDTO(
        Long id,
        String kodeMataKuliah,
        String nama,
        int sks,
        Set<DataPrs> dataRegistrasi
) {
}
