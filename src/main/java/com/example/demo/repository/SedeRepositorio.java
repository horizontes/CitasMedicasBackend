package com.example.demo.repository;

import com.example.demo.model.Sede;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepositorio extends CrudRepository<Sede, Integer> {
}
