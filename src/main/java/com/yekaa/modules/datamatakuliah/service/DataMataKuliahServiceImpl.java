package com.yekaa.modules.datamatakuliah.service;

import com.yekaa.modules.datamatakuliah.dto.DataMataKuliahRequestDTO;
import com.yekaa.modules.datamatakuliah.dto.DataMataKuliahResponseDTO;
import com.yekaa.modules.datamatakuliah.entity.DataMataKuliah;
import com.yekaa.modules.datamatakuliah.repository.DataMataKuliahRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

// service implementation
@ApplicationScoped
public class DataMataKuliahServiceImpl implements DataMataKuliahService {

    @Inject
    DataMataKuliahRepositoryImpl repository;

    @Override
    public List<DataMataKuliahResponseDTO> getAllDataMataKuliah() {
        List<DataMataKuliah> query = repository.listAll();

        List<DataMataKuliahResponseDTO> result = query.stream().map(
                x -> new DataMataKuliahResponseDTO(
                        x.getId(),
                        x.getKode(),
                        x.getNama(),
                        x.getSks())
        ).toList();

        return result;
    }

    @Override
    public Optional<DataMataKuliahResponseDTO> getDataMataKuliahById(Long id) {
        Optional<DataMataKuliah> query = repository.findByIdOptional(id);

        Optional<DataMataKuliahResponseDTO> result;

        if(query.isPresent()){
            result = Optional.of(new DataMataKuliahResponseDTO(
                    query.get().getId(),
                    query.get().getKode(),
                    query.get().getNama(),
                    query.get().getSks()
            ));
        } else {
            throw new EntityNotFoundException("DataMataKuliah with ID " + id + " not found");
        }

        return result;
    }


    @Override
    @Transactional
    public void createDataMataKuliah(DataMataKuliahRequestDTO dto) {

        DataMataKuliah query = new DataMataKuliah();
        query.setKode(dto.kode());
        query.setNama(dto.nama());
        query.setSks(dto.sks());

        repository.persist(query);
    }

    @Override
    @Transactional
    public void deleteDataMataKuliahById(Long id) {
        Optional<DataMataKuliah> existingRecord = repository.findByIdOptional(id);

        if(existingRecord.isPresent()){
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("DataMataKuliah with ID " + id + " not found");
        }
    }
}
