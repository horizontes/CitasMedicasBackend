package com.example.demo.repository;

import com.example.demo.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends CrudRepository<Rol, Integer> {
}
