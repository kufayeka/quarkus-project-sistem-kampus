package com.yekaa.modules.dataprs.dto;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataPrsResponseDTO(
        Long id,
        DataMahasiswa dataMahasiswa,
        DataMataKuliah dataMataKuliah,
        LocalDateTime tanggalPendaftaran,
        Boolean pendaftaranDiterima
) {
}
