package com.udemy.spring.cource.universityjava.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
/*utilizamos la anotacion data de lombok para que senos agregen atuomaticmente todos lo metodos setter and getters,
mas el toString mas el haschCode y esto sumado con la anotazion builder para permitirnos crear un objeto mas facilmente concateneando todos
todo con un .*/
public class Persona {

    private String name;
    private String lastName;
    private Long age;
    private String email;
}
