package com.yekaa.modules.datamahasiswa.repository;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataMahasiswaRepositoryImpl implements DataMahasiswaRepository{

    // custom queries
    public DataMahasiswa findByNrp(String nrp) {
        return find("nrp", nrp).firstResult();
    }
}
