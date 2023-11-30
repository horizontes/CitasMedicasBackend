package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

    @Query("""
            SELECT u.usuarioId,
            u.nombres,
            u.apellidos,
            u.clave,
            u.correo,
            
            u.creacion,
            u.actualizacion,
            u.estado
            FROM Usuario u
            """)
    Iterable<Usuario> findUsuarios();

    @Query("""
            SELECT u.usuarioId,
            u.nombres,
            u.apellidos,
            u.clave,
            u.correo,
            
            u.creacion,
            u.actualizacion,
            u.estado
            FROM Usuario u
            WHERE u.correo = :correo
            """)
    Iterable<Usuario> findUsuariosByCorreo(String correo);


}
