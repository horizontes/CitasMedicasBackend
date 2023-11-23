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
@SequenceGenerator(name="rol_id_seq", initialValue=1, allocationSize=100)
public class Rol {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="rol_id_seq")
    private int rolId;
    private String nombre;
    private int acceso;

    private Date creacion;
    private Date actualizacion;
    private int estado;

}
