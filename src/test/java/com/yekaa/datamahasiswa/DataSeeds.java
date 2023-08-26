package com.yekaa.datamahasiswa;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamahasiswa.repository.DataMahasiswaRepositoryImpl;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@Priority(1)
@Alternative
@ApplicationScoped
public class DataSeeds extends DataMahasiswaRepositoryImpl {

    @PostConstruct
    public void init(){
        persist(
                new DataMahasiswa(
                        1L,
                        "Sih Kawuryan Yulianes Kufa",
                        "c11180002",
                        "Teknik Elektro",
                        null),

                new DataMahasiswa(
                        2L,
                        "Gregorio Diovani Wahanie",
                        "c11180040",
                        "Teknik Elektro",
                        null
                )
        );
    }
}
