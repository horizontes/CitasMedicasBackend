package com.example.demo.repository.mapper;

import com.example.demo.model.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper  implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

        Usuario u = new Usuario();

        u.setUsuarioId(rs.getInt("usuario_id"));
        u.setClave(rs.getString("clave"));
        u.setApellidos(rs.getString("apellidos"));
        u.setCorreo(rs.getString("correo"));
        u.setNombres(rs.getString("nombres"));

        u.setActualizacion(rs.getDate("actualizacion"));
        u.setCreacion(rs.getDate("creacion"));
        u.setEstado(rs.getInt("estado"));

        return u;
    }
}
