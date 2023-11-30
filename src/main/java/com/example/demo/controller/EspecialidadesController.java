package com.example.demo.controller;

import com.example.demo.model.Citas;
import com.example.demo.model.Especialidad;
import com.example.demo.repository.EspecialidadJDBCRepositorio;
import com.example.demo.repository.EspecialidadRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadesController extends GenericController<Especialidad> {

    @Autowired
    EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    EspecialidadJDBCRepositorio especialidadJDBCRepositorio;

    @PostConstruct
    void init(){
        super.setRepo(especialidadRepositorio);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<Especialidad>> getItems() {
        return new ResponseEntity<>(
                especialidadJDBCRepositorio.findAll(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Especialidad> getItem(
                @PathVariable int id) {
        return new ResponseEntity<>(
                especialidadJDBCRepositorio.findById(id),
                HttpStatus.OK);
    }


}
