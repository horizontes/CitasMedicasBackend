package com.example.demo.controller;

import com.example.demo.model.Especialidad;
import com.example.demo.model.Sede;
import com.example.demo.repository.EspecialidadRepositorio;
import com.example.demo.repository.SedeRepositorio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/sedes")
public class SedesController extends GenericController<Sede> {

    @Autowired
    SedeRepositorio sedeRepositorio;

    @PostConstruct
    void init(){
        super.setRepo(sedeRepositorio);
    }


}
