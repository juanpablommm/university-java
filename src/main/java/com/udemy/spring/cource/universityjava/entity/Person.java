package com.udemy.spring.cource.universityjava.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
//inicamos que nuestra clase sera un clase dominio, o entity es decir que estara representado unaentidad de la basde detos
@Table(name = "person")
//indicamos con la anotacion table contra que tabla de la base de datos estara mapeando nuestra clase
public class Person implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    //indicamos que este atributo estara mapeando contra el id de la tabla de la base de datos, es decir que sera la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* indicamos cual es la forma de generar esta llave primaria, y tenemos que inicar cual sera la estrategia que utlizarmos para
    generar esta llave primaria, en este coaso IDENTITY*/
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

}
