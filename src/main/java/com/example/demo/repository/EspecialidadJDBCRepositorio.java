package com.example.demo.repository;


import com.example.demo.model.Especialidad;
import com.example.demo.model.Sede;
import com.example.demo.model.Usuario;
import com.example.demo.repository.mapper.UsuarioMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class EspecialidadJDBCRepositorio {

    @Autowired
    JdbcTemplate jt;

    public List<Especialidad> findAll(){
        return jt.query("""
                SELECT e.especialidad_id,
                    e.nombre,
                    e.creacion,
                    e.actualizacion,
                    e.estado
                     FROM Especialidad e
                """, (rs, rowNum) -> {
                    Especialidad e = new Especialidad();
                    e.setEspecialidadId(rs.getInt("especialidad_id"));
                    e.setNombre(rs.getString("nombre"));

                    e.setActualizacion(rs.getDate("actualizacion"));
                    e.setCreacion(rs.getDate("creacion"));
                    e.setEstado(rs.getInt("estado"));
                    return e;
                });
    }

    public Especialidad findById(int id){
        return jt.queryForObject("""
                EXEC dbo.SP_OBTENERESPECIALIDAD_ID ?
                """, new Object[]{ id }
                   , new int[] { Types.INTEGER }
                , (rs, rowNum) -> {
            Especialidad e = new Especialidad();
            e.setEspecialidadId(rs.getInt("especialidad_id"));
            e.setNombre(rs.getString("nombre"));

            e.setActualizacion(rs.getDate("actualizacion"));
            e.setCreacion(rs.getDate("creacion"));
            e.setEstado(rs.getInt("estado"));
            e.setSedes(new Gson().fromJson(rs.getString("sedes"), Sede[].class));

            return e;
        });
    }

}
