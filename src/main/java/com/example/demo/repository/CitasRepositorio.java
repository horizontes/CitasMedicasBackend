package com.example.demo.repository;

import com.example.demo.model.Citas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface CitasRepositorio extends CrudRepository<Citas, Integer> {

    @Query("""
            SELECT
            c.citaId,
            c.medico.medicoId,
            c.medico.nombreCompleto,
            c.usuario.usuarioId,
            c.sede.sedeId,
            c.especialidad.especialidadId,
            c.especialidad.nombre,
            c.fecha,
            c.hora,
            c.duracion,
            c.confirmado
             FROM Citas c
            """)
    Iterable<Citas> findAll();

    @Query("""
            SELECT
            c.citaId,
            c.medico.medicoId,
            c.medico.nombreCompleto,
            c.usuario.usuarioId,
            c.sede.sedeId,
            c.especialidad.especialidadId,
            c.especialidad.nombre,
            c.fecha,
            c.hora,
            c.duracion,
            c.confirmado
             FROM Citas c WHERE c.medico.medicoId = :medicoId
        """)
    Iterable<Citas> findByMedico(int medicoId);

    @Query("""
            SELECT
            c.citaId,
            c.medico.medicoId,
            c.medico.nombreCompleto,
            c.usuario.usuarioId,
            c.sede.sedeId,
            c.especialidad.especialidadId,
            c.especialidad.nombre,
            c.fecha,
            c.hora,
            c.duracion,
            c.confirmado
             FROM Citas c WHERE c.fecha = :fecha
            AND c.especialidad.especialidadId = :especialidadId
            """)
    Iterable<Citas> findByMedicoAndEspecialidad(int especialidadId
            , Date fecha);

}
