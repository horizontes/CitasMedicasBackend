package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="sede_id_seq", initialValue=1, allocationSize=100)
public class Sede {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sede_id_seq")
    private Integer usuarioId;

    private String nombre;
    private String direccion;
    private String distrito;
    private String coordenadas;
    private String referencia;

    private Date creacion;
    private Date actualizacion;
    private int estado;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Sede_Especialidad",
            joinColumns = { @JoinColumn(name = "sede_id") },
            inverseJoinColumns = { @JoinColumn(name = "especialidad_id") }
    )
    Set<Especialidad> especialidades;

}
