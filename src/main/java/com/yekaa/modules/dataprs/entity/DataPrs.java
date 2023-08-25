package com.yekaa.modules.dataprs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "data_prs")
public class DataPrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_mahasiswa_id")
    @JsonBackReference
    private DataMahasiswa dataMahasiswa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_mata_kuliah_id")
    @JsonBackReference
    private DataMataKuliah dataMataKuliah;

    private LocalDateTime tanggalPendaftaran;

    private Boolean pendaftaranDiterima = Boolean.FALSE;

    @PrePersist
    public void prePersist(){
        dataMahasiswa.getDataRegistrasi().add(this);
        dataMataKuliah.getDataRegistrasi().add(this);
    }

}
