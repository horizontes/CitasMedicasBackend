package com.example.demo.controller;


import com.example.demo.model.Citas;
import com.example.demo.repository.CitasRepositorio;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CitasController{

    @PostConstruct
    void init(){

        Gson gson = new Gson();
        try (BufferedReader reader =
                     new BufferedReader(new FileReader("src/main/resources/static/citas.json"))) {

            Citas[] cs = gson.fromJson(reader, Citas[].class);
            Arrays.stream(cs).forEach(repo::save);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Autowired
    CitasRepositorio repo;

    @GetMapping("/citas")
    public ResponseEntity<List<Citas>> getCitas() {

        List<Citas> citas = StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .collect(Collectors.toList());

        if (!citas.isEmpty()) {
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/citas/{id}")
    public ResponseEntity<Citas> getCitas(@PathVariable("id") Integer id) {

        Optional<Citas> tutorialData = repo.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/citas")
    public ResponseEntity<Citas> crearCitas(@RequestBody Citas cita) {
        try {
            Citas _citas = repo.save(cita);
            return new ResponseEntity<>(_citas, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}