package com.yekaa.modules.datamahasiswa.entity;

import com.yekaa.modules.dataprs.entity.DataPrs;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "data_mahasiswa")
public class DataMahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    @Column(unique = true)
    private String nrp;
    private String jurusan;

    @OneToMany(mappedBy = "dataMahasiswa", cascade = CascadeType.ALL)
    private Set<DataPrs> dataRegistrasi = new HashSet<>();

}
