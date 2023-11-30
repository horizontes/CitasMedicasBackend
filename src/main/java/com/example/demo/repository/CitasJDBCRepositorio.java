package com.example.demo.repository;

import com.example.demo.model.Citas;
import com.example.demo.repository.mapper.CitasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Types;
import java.util.List;

@Repository
public class CitasJDBCRepositorio{

    @Autowired
    JdbcTemplate jt;

    public List<Citas> obtenerCitas(){
        return jt.query("""
                SELECT c.actualizacion, c.cita_id, c.confirmado,
                	c.creacion, c.duracion, c.estado, c.fecha, c.hora,
                	m.medico_id, m.nombre_completo, c.sede_id,
                	e.especialidad_id, e.nombre AS especialidad_nombre,
                	c.fecha, c.hora, c.duracion, c.confirmado
                	FROM citas AS c INNER JOIN medico AS m
                	ON c.medico_id = m.medico_id
                	INNER JOIN especialidad AS e
                	ON c.especialidad_id = e.especialidad_id;
                """, new CitasMapper());
    }

    public List<Citas> obtenerCitasPorMedico(int medicoId){
        return jt.query("""
                SELECT c.actualizacion, c.cita_id, c.confirmado,
                	c.creacion, c.duracion, c.estado, c.fecha, c.hora,
                	m.medico_id, m.nombre_completo, c.sede_id,
                	e.especialidad_id, e.nombre AS especialidad_nombre,
                	c.fecha, c.hora, c.duracion, c.confirmado
                	FROM citas AS c INNER JOIN medico AS m
                	ON c.medico_id = m.medico_id
                	INNER JOIN especialidad AS e
                	ON c.especialidad_id = e.especialidad_id
                	WHERE c.medicoId = ?;
                """, new Object[] { medicoId }, new int[] { Types.INTEGER },
                new CitasMapper());
    }

    public List<Citas> obtenerCitasPorEspecialidad(
            int especialidad_id, Date fecha){
        return jt.query("""
                        EXEC [dbo].[SP_OBTENERCITAS_ESPECIALIDAD] ?, ?
                        """, new Object[] { especialidad_id, fecha },
                new int[] { Types.INTEGER, Types.DATE},
                new CitasMapper());
    }

}
