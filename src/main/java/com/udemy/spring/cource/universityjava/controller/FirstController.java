package com.udemy.spring.cource.universityjava.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller("/")
public record FirstController() {

    @GetMapping("*")
    public String index(){
        log.debug("ingresamos al end-point del index.estamos jecutando un controlador Sprign MVC");
        return "index";
    }

}
