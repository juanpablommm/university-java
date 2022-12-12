package com.udemy.spring.cource.universityjava.controller;


import com.udemy.spring.cource.universityjava.entity.Persona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller("/")
public class FirstController {
    @Value("${index.greeting}")
    private String greetinProperties;

    @GetMapping("*")
    public String index(Model model){
        log.debug("ingresamos al end-point del index.estamos jecutando un controlador Sprign MVC");
        Persona persona = Persona.builder().
        name("Criss").lastName("Turner").email("CrissTurner@gmail.com").age(22L)
        .build();
        model.addAttribute("persona", persona);
        //creamos nuestro objeto persona de la clase dominio y mandamos el objecto a la template
        return "index";
    }

}
