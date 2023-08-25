package com.yekaa.modules.datamatakuliah.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "data_mata_kuliah")
public class DataMataKuliah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String kode;
    @Column(nullable = false)
    private String nama;
    private int sks;
}
