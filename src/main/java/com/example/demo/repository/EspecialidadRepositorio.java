package com.example.demo.repository;

import com.example.demo.model.Especialidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepositorio extends CrudRepository<Especialidad, Integer> {
}
