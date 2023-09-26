package com.example.demo.repository;

import com.example.demo.model.Citas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitasRepositorio extends CrudRepository<Citas, Integer> {
}
