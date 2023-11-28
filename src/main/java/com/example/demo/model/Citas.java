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
@SequenceGenerator(name="cita_id_seq", allocationSize=100)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cita_id", "especialidad_id",
        "medico_id", "usuario_id", "fecha",  "hora" }) })
public class Citas {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cita_id_seq")
    @Column(name = "cita_id")
    private Integer citaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", referencedColumnName = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id", referencedColumnName = "sede_id")
    private Sede sede;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id", referencedColumnName = "especialidad_id")
    private Especialidad especialidad;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora")

    private String hora;
    private Integer duracion;
    private boolean confirmado;

    private Date creacion;
    private Date actualizacion;
    private int estado;

}
