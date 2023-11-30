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

    public Sede(int sedeId){
        this.sedeId = sedeId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sede_id_seq")
    @Column(name = "sede_id")
    private Integer sedeId;

    @Column(columnDefinition = "NVARCHAR(255) COLLATE Modern_Spanish_CI_AS")
    private String nombre;

    @Column(columnDefinition = "NVARCHAR(255) COLLATE Modern_Spanish_CI_AS")
    private String direccion;

    @Column(columnDefinition = "NVARCHAR(255) COLLATE Modern_Spanish_CI_AS")
    private String distrito;

    private String coordenadas;

    @Column(columnDefinition = "NVARCHAR(255) COLLATE Modern_Spanish_CI_AS")
    private String referencia;

    private Date creacion;
    private Date actualizacion;
    private int estado;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Sede_Especialidad",
            joinColumns = { @JoinColumn(name = "sede_id") },
            inverseJoinColumns = { @JoinColumn(name = "especialidad_id") }
    )
    Set<Especialidad> especialidades;

}
