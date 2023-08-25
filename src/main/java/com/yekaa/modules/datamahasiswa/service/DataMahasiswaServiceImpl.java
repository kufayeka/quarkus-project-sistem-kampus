package com.yekaa.modules.datamahasiswa.service;

import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaRequestDTO;
import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaResponseDTO;
import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamahasiswa.repository.DataMahasiswaRepository;
import com.yekaa.modules.datamahasiswa.repository.DataMahasiswaRepositoryImpl;
import jakarta.ejb.DuplicateKeyException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// service implementation
@ApplicationScoped
public class DataMahasiswaServiceImpl implements DataMahasiswaService {

    @Inject
    DataMahasiswaRepositoryImpl repository;

    @Override
    public List<DataMahasiswaResponseDTO> getAllDataMahasiswa() {
        List<DataMahasiswa> query = repository.listAll();

        List<DataMahasiswaResponseDTO> result = query.stream().map(
                x -> new DataMahasiswaResponseDTO(
                        x.getId(),
                        x.getNama(),
                        x.getNrp(),
                        x.getJurusan())
        ).toList();

        return result;
    }

    @Override
    public Optional<DataMahasiswaResponseDTO> getDataMahasiswaById(Long id) {
        Optional<DataMahasiswa> query = repository.findByIdOptional(id);

        Optional<DataMahasiswaResponseDTO> result;

        if(query.isPresent()){
            result = Optional.of(new DataMahasiswaResponseDTO(
                    query.get().getId(),
                    query.get().getNama(),
                    query.get().getNrp(),
                    query.get().getJurusan()
            ));
        } else {
            throw new EntityNotFoundException("DataMahasiswa with ID " + id + " not found");
        }

        return result;
    }

    @Override
    public Optional<DataMahasiswaResponseDTO> getDataMahasiswaByNrp(String nrp) {
        Optional<DataMahasiswa> query = Optional.ofNullable(repository.findDataMahasiswaByNrp(nrp));

        Optional<DataMahasiswaResponseDTO> result;

        if(query.isPresent()){
            result = Optional.of(new DataMahasiswaResponseDTO(
                    query.get().getId(),
                    query.get().getNama(),
                    query.get().getNrp(),
                    query.get().getJurusan()
            ));
        } else {
            throw new EntityNotFoundException("DataMahasiswa with ID " + nrp + " not found");
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
            throw new EntityNotFoundException("DataMahasiswa with ID " + id + " not found");
        }
    }
}
