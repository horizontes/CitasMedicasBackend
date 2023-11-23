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
@SequenceGenerator(name="medico_id_seq", initialValue=1, allocationSize=100)
public class Medico {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medico_id_seq")
    private Integer medicoId;
    private String nombres;
    private String apellidos;
    private String clave;
    private String correo;
    private String telefono;
    private String cmp;
    private int experiencia;

    private Date creacion;
    private Date actualizacion;
    private int estado;

}