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
@SequenceGenerator(name="especialidad_id_seq", initialValue=1, allocationSize=100)
public class Especialidad {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="especialidad_id_seq")
    private Integer especialidadId;

    private String nombre;

    private Date creacion;
    private Date actualizacion;
    private int estado;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Especialidad_Medico",
            joinColumns = { @JoinColumn(name = "especialidad_id") },
            inverseJoinColumns = { @JoinColumn(name = "medico_id") }
    )
    Set<Medico> medicos;

}
