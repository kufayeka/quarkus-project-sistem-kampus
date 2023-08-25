package com.yekaa.modules.datamatakuliah.repository;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataMataKuliahRepositoryImpl implements DataMataKuliahRepository {

    // custom queries
    public DataMataKuliah findByKodeMataKuliah(String kodeMataKuliah) {
        return find("kodeMataKuliah", kodeMataKuliah).firstResult();
    }

}
