package com.udemy.spring.cource.universityjava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* indicamos cual es la forma de generar esta llave primaria, y tenemos que inicar cual sera la estrategia que utlizarmos para
    generar esta llave primaria, en este coaso IDENTITY*/
    private Long id;


    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "phone_number")
    private Long phoneNumber;

    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;

}
