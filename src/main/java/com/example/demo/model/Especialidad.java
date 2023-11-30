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

    public Especialidad(int especialidadId){
        this.especialidadId = especialidadId;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="especialidad_id_seq")
    @Column(name = "especialidad_id")
    private Integer especialidadId;

    @Column(columnDefinition = "NVARCHAR(255) COLLATE Modern_Spanish_CI_AS", unique = true)
    private String nombre;

    private Date creacion;
    private Date actualizacion;
    private int estado;

    @Transient
    private Sede[] sedes;

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Especialidad_Medico",
            joinColumns = { @JoinColumn(name = "especialidad_id") },
            inverseJoinColumns = { @JoinColumn(name = "medico_id") }
    )
    Set<Medico> medicos;

}
