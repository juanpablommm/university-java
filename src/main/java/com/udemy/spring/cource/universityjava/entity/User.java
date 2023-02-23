package com.udemy.spring.cource.universityjava.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username")
    private String username;


    @NotEmpty
    @Column(name = "password")
    private String passsword;


    @OneToMany /*dato que en nuestra base de datos tenemos una realzacion deuno muchos
    en donde un usuario puede tener un o muchos roles, entonces nos apoyamos de la anotacion
    OneToMany indicado de uno a muchos.
    y con la anotacion JoinColum mencioamos el nombre del atributo de la entity con la que tenemos
     esa relacion, es decir nuestra foreng-ke.
     Nuestro atributo es un Lista de roles presisamente porque es una realcionOneToMany*/
    @JoinColumn(name = "id_user")
    private List<Rol> rol;
}
