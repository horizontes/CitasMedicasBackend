package com.example.demo.repository;

import com.example.demo.model.Especialidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepositorio extends CrudRepository<Especialidad, Integer> {

    @Query("SELECT e.especialidadId,\n" +
            "e.nombre,\n" +
            "e.creacion,\n" +
            "e.actualizacion,\n" +
            "e.estado\n" +
            " FROM Especialidad e\n")
    Iterable<Especialidad> findAll();

}
