package com.yekaa.modules.datamahasiswa.repository;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.stereotype.Repository;

public interface DataMahasiswaRepository extends PanacheRepository<DataMahasiswa> { }
