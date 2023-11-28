package com.example.demo.repository;

import com.example.demo.model.Medico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorio extends CrudRepository<Medico, Integer> {
}
