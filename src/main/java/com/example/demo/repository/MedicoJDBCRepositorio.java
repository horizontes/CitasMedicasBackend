package com.example.demo.repository;


import com.example.demo.model.Medico;
import com.example.demo.model.Usuario;
import com.example.demo.repository.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class MedicoJDBCRepositorio {

    @Autowired
    JdbcTemplate jt;

    public List<Medico> findMedicosPorSedeFecha(Date fecha,
                        int especialidadId, int sedeId){
        return jt.query("""
                EXEC dbo.SP_OBTENERMEDICOS_SEDE_FECHA  ?, ?, ?
                """
                , new Object[]{ fecha, especialidadId, sedeId}
                , new int[] {Types.DATE, Types.INTEGER, Types.INTEGER },
                (rs, rowNum) -> {

                    Medico m = new Medico();
                    m.setMedicoId(rs.getInt("medico_id"));
                    m.setClave(rs.getString("clave"));
                    m.setNombreCompleto(rs.getString("nombre_completo"));
                    m.setCorreo(rs.getString("correo"));
                    m.setCmp(rs.getString("cmp"));
                    m.setExperiencia(rs.getInt("experiencia"));
                    m.setColaPacientes(rs.getInt("colaPacientes"));

                    m.setActualizacion(rs.getDate("actualizacion"));
                    m.setCreacion(rs.getDate("creacion"));
                    m.setEstado(rs.getInt("estado"));

                    return m;
                });
    }

}
