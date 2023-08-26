package com.yekaa.modules.dataprs.service;

import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamahasiswa.repository.DataMahasiswaRepositoryImpl;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import com.yekaa.modules.datamatakuliah.repository.DataMataKuliahRepositoryImpl;
import com.yekaa.modules.dataprs.dto.DataPrsRequestDTO;
import com.yekaa.modules.dataprs.dto.DataPrsResponseDTO;
import com.yekaa.modules.dataprs.entity.DataPrs;
import com.yekaa.modules.dataprs.repository.DataPrsRepositoryImpl;
import com.yekaa.common.util.ErrorMessageUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// service implementation
@ApplicationScoped
public class DataPrsServiceImpl implements DataPrsService {

    @Inject
    DataPrsRepositoryImpl repository;

    @Inject
    DataMahasiswaRepositoryImpl repositoryDataMahasiswa;

    @Inject
    DataMataKuliahRepositoryImpl repositoryDataMataKuliah;

    @Inject
    ErrorMessageUtil errorMessageUtil;

    @Override
    public List<DataPrsResponseDTO> getAllDataPrs() {
        List<DataPrs> query = repository.listAll();

        List<DataPrsResponseDTO> result = query.stream().map(
                x -> new DataPrsResponseDTO(
                        x.getId(),
                        x.getDataMahasiswa(),
                        x.getDataMataKuliah(),
                        x.getTanggalPendaftaran(),
                        x.getPendaftaranDiterima())
        ).toList();

        return result;
    }

    @Override
    public Optional<DataPrsResponseDTO> getDataPrsById(Long id) {
        Optional<DataPrs> query = repository.findByIdOptional(id);

        Optional<DataPrsResponseDTO> result = null;

        if(query.isPresent()){
            result = Optional.of(new DataPrsResponseDTO(
                    query.get().getId(),
                    query.get().getDataMahasiswa(),
                    query.get().getDataMataKuliah(),
                    query.get().getTanggalPendaftaran(),
                    query.get().getPendaftaranDiterima()
            ));
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataPrs", id.toString());
        }

        return result;
    }


    @Override
    @Transactional
    public void createDataPrs(DataPrsRequestDTO dto) {
        DataMahasiswa dataMahasiswaExisting =
                repositoryDataMahasiswa.findByNrp(dto.nrpMahasiswa());

        if (dataMahasiswaExisting != null) {
            return;
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataMahasiswa", dto.nrpMahasiswa());
        }

        DataMataKuliah dataMataKuliahExisting =
                repositoryDataMataKuliah.findByKodeMataKuliah(dto.kodeMataKuliah());

        if (dataMataKuliahExisting != null) {
            return;
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataMataKuliah", dto.kodeMataKuliah());
        }

        DataPrs query = new DataPrs();
        query.setDataMahasiswa(dataMahasiswaExisting);
        query.setDataMataKuliah(dataMataKuliahExisting);
        query.setTanggalPendaftaran(LocalDateTime.now());
        repository.persist(query);
    }

    @Override
    @Transactional
    public void deleteDataPrsById(Long id) {
        Optional<DataPrs> existingRecord = repository.findByIdOptional(id);

        if(existingRecord.isPresent()){
            repository.deleteById(id);
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataPrs",id.toString());
        }
    }
}
