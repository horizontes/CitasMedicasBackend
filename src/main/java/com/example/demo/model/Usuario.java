package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name="usuario_id_seq", initialValue=1, allocationSize=100)
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usuario_id_seq")
    private Integer usuarioId;
    private String nombres;
    private String apellidos;
    private String clave;
    private String correo;
    private String creacion;
    private String ultimoIngreso;

}
