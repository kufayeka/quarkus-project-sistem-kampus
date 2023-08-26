package com.yekaa.modules.datamahasiswa.service;

import com.yekaa.common.exception.DataNotFoundException;
import com.yekaa.common.util.ErrorMessageUtil;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaRequestDTO;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaResponseDTO;
import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamahasiswa.repository.DataMahasiswaRepository;
import com.yekaa.modules.datamahasiswa.repository.DataMahasiswaRepositoryImpl;
import com.yekaa.modules.dataprs.entity.DataPrs;
import jakarta.ejb.DuplicateKeyException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// service implementation
@ApplicationScoped
public class DataMahasiswaServiceImpl implements DataMahasiswaService {

    @Inject
    DataMahasiswaRepositoryImpl repository;

    @Inject
    ErrorMessageUtil errorMessageUtil;

    @Override
    public List<DataMahasiswaResponseDTO> getAllDataMahasiswa() {
        List<DataMahasiswa> query = repository.listAll();
        List<DataMahasiswaResponseDTO> result = null;

        if(!query.isEmpty()){
            result = query.stream().map(
                    x -> new DataMahasiswaResponseDTO(
                            x.getId(),
                            x.getNama(),
                            x.getNrp(),
                            x.getJurusan(),
                            x.getDataRegistrasi())
            ).toList();
        } else {
            errorMessageUtil.throwFindAllNotFoundError("dataMahasiswa");
        }
        return result;
    }

    @Override
    public Optional<DataMahasiswaResponseDTO> getDataMahasiswaById(Long id) {
        Optional<DataMahasiswa> query = repository.findByIdOptional(id);

        Optional<DataMahasiswaResponseDTO> result = Optional.empty();

        if(query.isPresent()){
            result = Optional.of(new DataMahasiswaResponseDTO(
                    query.get().getId(),
                    query.get().getNama(),
                    query.get().getNrp(),
                    query.get().getJurusan(),
                    query.get().getDataRegistrasi()
            ));
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataMahasiswa", id.toString());
        }
        return result;
    }

    @Override
    public Optional<DataMahasiswaResponseDTO> getDataMahasiswaByNrp(String nrp) {
        Optional<DataMahasiswa> query = Optional.ofNullable(repository.findByNrp(nrp));

        Optional<DataMahasiswaResponseDTO> result = Optional.empty();

        if(query.isPresent()){
            result = Optional.of(new DataMahasiswaResponseDTO(
                    query.get().getId(),
                    query.get().getNama(),
                    query.get().getNrp(),
                    query.get().getJurusan(),
                    query.get().getDataRegistrasi()
            ));
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataMahasiswa", nrp);
        }

        return result;
    }

    @Override
    @Transactional
    public void createDataMahasiswa(DataMahasiswaRequestDTO dto) {

        DataMahasiswa query = new DataMahasiswa();
        query.setNama(dto.nama());
        query.setNrp(dto.nrp());
        query.setJurusan(dto.jurusan());

        repository.persist(query);
    }

    @Override
    @Transactional
    public void deleteDataMahasiswaById(Long id) {
        Optional<DataMahasiswa> existingRecord = repository.findByIdOptional(id);

        if(existingRecord.isPresent()){
            repository.deleteById(id);
        } else {
            errorMessageUtil.throwFindByIdNotFoundError("dataMahasiswa", id.toString());
        }
    }
}
