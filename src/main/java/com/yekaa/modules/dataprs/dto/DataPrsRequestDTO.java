package com.yekaa.modules.dataprs.dto;

import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaRequestDTO;
import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataPrsRequestDTO (
        @NotBlank
        String nrpMahasiswa,
        @NotBlank
        String kodeMataKuliah
) {
}
