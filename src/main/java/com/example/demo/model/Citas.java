package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="cita_id_seq", initialValue=1, allocationSize=100)
public class Citas {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cita_id_seq")
    private Integer citaId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sede sede;

    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;

    private String fecha;
    private String hora;
    private Integer duracion;
    private boolean confirmado;

    private Date creacion;
    private Date actualizacion;
    private int estado;

}
