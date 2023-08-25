package com.yekaa.modules.dataprs.entity;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "data_prs")
public class DataPrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_mahasiswa_id")
    private DataMahasiswa dataMahasiswa;

    @ManyToOne
    @JoinColumn(name = "data_mata_kuliah_id")
    private DataMataKuliah dataMataKuliah;

    private LocalDateTime tanggalPendaftaran;

    private Boolean pendaftaranDiterima = Boolean.FALSE;
}
