package com.example.demo.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class GenericController<T> {

    private CrudRepository<T, Integer> repo;

    void setRepo(CrudRepository<T, Integer> repo){
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<T>> getItems(){
        return new ResponseEntity<>(
                StreamSupport.stream(repo.findAll()
                                .spliterator(), false)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getItem(@PathVariable int id){

        return repo.findById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.OK));

    }

    @PostMapping
    public ResponseEntity<T> updateItem(@RequestBody T t){

        return new ResponseEntity<>(repo.save(t), HttpStatus.OK);

    }

}
