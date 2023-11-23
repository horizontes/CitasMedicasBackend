package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

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

    private Date creacion;
    private Date actualizacion;
    private int estado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "usuario_id" //, referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "rol_id" //, referencedColumnName = "id"
            )
    )
    private Set<Rol> rol;

}
