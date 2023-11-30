package com.example.demo.controller;

import com.example.demo.model.Citas;
import com.example.demo.model.Especialidad;
import com.example.demo.model.Medico;
import com.example.demo.model.Sede;
import com.example.demo.repository.EspecialidadRepositorio;
import com.example.demo.repository.MedicoJDBCRepositorio;
import com.example.demo.repository.MedicoRepositorio;
import com.example.demo.repository.SedeRepositorio;
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
@RequestMapping("/api/medicos")
public class MedicosController extends GenericController<Medico> {

    @Autowired
    MedicoRepositorio medicoRepositorio;

    @Autowired
    MedicoJDBCRepositorio medicoJDBCRepositorio;

    @PostConstruct
    void init(){
        super.setRepo(medicoRepositorio);
    }

    @GetMapping("/especialidad")
    public ResponseEntity<List<Medico>> getMedicos(
            @RequestParam int especialidadId, @RequestParam Date fecha,
            @RequestParam int sedeId) {
        return new ResponseEntity<>(
                medicoJDBCRepositorio
                        .findMedicosPorSedeFecha(fecha, especialidadId, sedeId),
                HttpStatus.OK);
    }


}
