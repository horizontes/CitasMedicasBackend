package com.example.demo.repository;

import com.example.demo.model.Sede;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepositorio extends CrudRepository<Sede, Integer> {

    @Query("""
            SELECT s.sedeId,
            s.nombre,
            s.direccion,
            s.distrito,
            s.coordenadas,
            s.referencia,
            s.creacion,
            s.actualizacion,
            s.estado
            FROM Sede s
            """)
    Iterable<Sede> findAll();

}
