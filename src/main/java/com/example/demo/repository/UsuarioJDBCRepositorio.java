package com.example.demo.repository;

import com.example.demo.model.Citas;
import com.example.demo.model.Usuario;
import com.example.demo.repository.mapper.CitasMapper;
import com.example.demo.repository.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Types;
import java.util.List;

@Repository
public class UsuarioJDBCRepositorio {

    @Autowired
    JdbcTemplate jt;

    public List<Usuario> findUsuarios(){
        return jt.query("""
                SELECT u.usuario_id,
                u.nombres,
                u.apellidos,
                u.clave,
                u.correo,
                
                u.creacion,
                u.actualizacion,
                u.estado
                
                FROM Usuario u
                """, new UsuarioMapper());
    }

    public Usuario findUsuarioByCorreo(String correo){
        return jt.queryForObject("""
                SELECT u.usuario_id,
                u.nombres,
                u.apellidos,
                u.clave,
                u.correo,
                
                u.creacion,
                u.actualizacion,
                u.estado
                FROM Usuario u WHERE u.correo = ?;
                """, new Object[] { correo }, new int[] {Types.VARCHAR},
                new UsuarioMapper());
    }

    public Usuario validarUsuario(String correo, String clave) throws Exception{
        return jt.queryForObject("""
                        EXEC [dbo].[SP_LOGIN_VALIDAR] ?, ?
                        """, new Object[] { correo, clave },
                new int[] { Types.VARCHAR, Types.VARCHAR},
                new UsuarioMapper());
    }

}
