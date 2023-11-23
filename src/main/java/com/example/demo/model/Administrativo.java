package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
//@Entity
@NoArgsConstructor
@AllArgsConstructor
//@DiscriminatorValue("2")
//@PrimaryKeyJoinColumn(name = "administrativoId")
public class Administrativo { //extends Usuario{

    private int cargo;

}
