package com.udemy.spring.cource.universityjava.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Person {

    private String name;
    private String lastName;
    private Long age;
    private String email;
}
