package com.RPL.SiapSidang.Penguji.NilaiMahasiswa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PengujiNilaiService {

    @Autowired
    private PengujiNilaiRepository repository;

    public PengujiNilai getNilaiById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void saveNilai(PengujiNilai nilai) {
        repository.save(nilai);
    }

    public void deleteNilai(Long id) {
        repository.deleteById(id);
    }
}
