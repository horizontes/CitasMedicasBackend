package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
//@Entity
@NoArgsConstructor
@AllArgsConstructor
//@SequenceGenerator(name="paciente_id_seq", initialValue=1, allocationSize=100)
public class Paciente {

    //@Id
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="paciente_id_seq")
    //private Integer pacienteId;
    private String nombres;
    private String apellidos;
    private String clave;
    private String correo;
    private int tipoPaciente;

    private Date creacion;
    private Date actualizacion;
    private int estado;

}
