package com.udemy.spring.cource.universityjava.controller;


import com.udemy.spring.cource.universityjava.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller("/")
public class FirstController {


    @GetMapping("*")
    public String index(Model model){
        log.debug("ingresamos al end-point del index.estamos jecutando un controlador Sprign MVC");
        Person person = Person.builder().
        name("Criss").lastName("Turner").email("CrissTurner@gmail.com").age(22L)
        .build();
        Person person2 = Person.builder().name("Emma").lastName("Sirus").age(25L).email("emmaSirus@gmail.com").build();
       // List<Person> people = new ArrayList<>();
        List<Person> people = Arrays.asList(person, person2);
        model.addAttribute("people", people);
        return "index";
    }

}
