package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioJDBCRepositorio;
import com.example.demo.repository.UsuarioRepositorio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController extends GenericController<Usuario> {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    UsuarioJDBCRepositorio usuarioJDBCRepositorio;

    @PostConstruct
    void init(){
        super.setRepo(usuarioRepositorio);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Usuario>> getItems(){
        return new ResponseEntity<>(
                usuarioJDBCRepositorio.findUsuarios(),
                HttpStatus.OK);
    }

    @GetMapping("/correo")
    public ResponseEntity<Usuario> getItem(
            @RequestParam String correo){
        return new ResponseEntity<>(
                usuarioJDBCRepositorio.findUsuarioByCorreo(correo),
                HttpStatus.OK);
    }

    @PostMapping("/validarUsuario")
    public ResponseEntity<Usuario> getItem(
            @RequestBody UsuarioView us){

        Usuario u = null;
        try {
            u = usuarioJDBCRepositorio.validarUsuario(us.correo, us.clave);
        } catch (Exception e) {
            e.fillInStackTrace();
            return new ResponseEntity<>(HttpStatus.OK);
        }

        if (Objects.isNull(u)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(u, HttpStatus.OK);
        }

    }

}

@Getter
@Setter
class UsuarioView{
    String correo;
    String clave;
}
