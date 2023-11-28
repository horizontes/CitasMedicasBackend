package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name="medico_id_seq", initialValue=1, allocationSize=100)
public class Medico {

    public Medico(int medicoId){
        this.medicoId = medicoId;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medico_id_seq")
    @Column(name = "medico_id")
    private Integer medicoId;

    @Column(columnDefinition = "NVARCHAR(255) COLLATE Modern_Spanish_CI_AS")
    private String nombreCompleto;

    private String clave;
    private String correo;
    private String telefono;
    private String cmp;
    private int experiencia;

    private Date creacion;
    private Date actualizacion;
    private int estado;

}