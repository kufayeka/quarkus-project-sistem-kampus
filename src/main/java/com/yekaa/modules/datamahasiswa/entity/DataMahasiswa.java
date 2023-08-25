package com.yekaa.modules.datamahasiswa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yekaa.modules.dataprs.entity.DataPrs;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "data_mahasiswa")
public class DataMahasiswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    @Column(unique = true)
    private String nrp;
    private String jurusan;

    @OneToMany(mappedBy = "dataMahasiswa", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<DataPrs> dataRegistrasi = new HashSet<>();

}
