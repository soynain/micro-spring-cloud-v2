package com.usermicroservice.main.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 350,nullable=false)
    @NotBlank(message = "Debe colocar su nombre completo")
    private String nombre;

    @Column(length = 500,nullable=false)
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @Column
    @NotBlank(message = "La contraseña es obligatoria")
    private String contra;
}
