package com.yekaa.modules.datamatakuliah.entity;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.dataprs.entity.DataPrs;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "data_mata_kuliah")
public class DataMataKuliah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kodeMataKuliah;
    @Column(nullable = false)
    private String nama;
    private int sks;

    @OneToMany(mappedBy = "dataMataKuliah", cascade = CascadeType.ALL)
    private Set<DataPrs> dataRegistrasi = new HashSet<>();

}
