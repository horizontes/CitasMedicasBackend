package com.example.demo.repository.mapper;

import com.example.demo.model.Citas;
import com.example.demo.model.Especialidad;
import com.example.demo.model.Medico;
import com.example.demo.model.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CitasMapper implements RowMapper<Citas> {
    @Override
    public Citas mapRow(ResultSet rs, int rowNum) throws SQLException {

        Citas c = new Citas();
        c.setCitaId(rs.getInt("cita_id"));
        c.setHora(rs.getString("hora"));
        c.setFecha(rs.getDate("fecha"));
        c.setConfirmado(rs.getBoolean("confirmado"));
        c.setDuracion(rs.getInt("duracion"));

        c.setMedico(new Medico(rs.getInt("medico_id")));
        c.getMedico().setNombreCompleto(rs.getString("nombre_completo"));

        c.setUsuario(new Usuario(rs.getInt("usuario_id")));

        c.setEspecialidad(new Especialidad(rs.getInt("especialidad_id")));
        c.getEspecialidad().setNombre(rs.getString("especialidad_nombre"));

        c.setActualizacion(rs.getDate("actualizacion"));
        c.setCreacion(rs.getDate("creacion"));
        c.setEstado(rs.getInt("estado"));

        return c;
    }
}
