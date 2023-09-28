package com.example.demo.controller;


import com.example.demo.model.Citas;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CitasRepositorio;
import com.example.demo.repository.UsuarioRepositorio;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsuarioController{

    @PostConstruct
    void init(){

        Gson gson = new Gson();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("src/main/resources/static/usuarios.json"))) {

            Usuario[] cs = gson.fromJson(reader, Usuario[].class);
            Arrays.stream(cs).forEach(repo::save);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Autowired
    UsuarioRepositorio repo;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuario() {

        List<Usuario> citas = StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (!citas.isEmpty()) {
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Integer id) {

        Optional<Usuario> usuarios = repo.findById(id);

        if (usuarios.isPresent()) {
            return new ResponseEntity<>(usuarios.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/validarUsuario")
    public ResponseEntity<Usuario> validarUsuario(@RequestBody Usuario usuario) {

        Usuario u = StreamSupport.stream(repo.findAll().spliterator(), false)
                .filter(x -> x.getCorreo().equals(usuario.getCorreo())).findFirst()
                .orElse(null);

        if (!Objects.isNull(u) && u.getClave().equals(usuario.getClave())) {
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario _usuario = repo.save(usuario);
            return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}