package com.example.demo.controller;

import com.example.demo.model.Citas;
import com.example.demo.repository.CitasJDBCRepositorio;
import com.example.demo.repository.CitasRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/citas")
public class CitasController extends GenericController<Citas> {

    @Autowired
    CitasRepositorio citasRepositorio;

    @Autowired
    CitasJDBCRepositorio citasJDBCRepositorio;

    @PostConstruct
    void init(){
        super.setRepo(citasRepositorio);
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<Citas>> getCitas(
            @RequestParam int especialidadId, @RequestParam Date fecha) {
          return new ResponseEntity<>(
                citasJDBCRepositorio
                        .obtenerCitasPorEspecialidad(especialidadId, fecha),
                HttpStatus.OK);
    }

    @GetMapping("/medico")
    public ResponseEntity<List<Citas>> getCitas(
            @RequestParam int medicoId) {
        /**
        return new ResponseEntity<>(
                StreamSupport.stream(citasRepositorio
                                .findByMedico(medicoId)
                                .spliterator(), false)
                        .collect(Collectors.toList()), HttpStatus.OK);
         **/
        return new ResponseEntity<>(
                citasJDBCRepositorio
                        .obtenerCitasPorMedico(medicoId),
                HttpStatus.OK);

    }

}
