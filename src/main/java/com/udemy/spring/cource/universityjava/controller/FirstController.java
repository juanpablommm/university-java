package com.udemy.spring.cource.universityjava.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller("/")
public class FirstController {

    /*podemos acceder a atributos que definamos en el archivo .properties, y asinarlo a alguna varible que tengamos,
    * a travez de la anotasion @Value de spring; debemos fijarnos de importar la anotacion correcta, es decir la de spsring
    * y no la de lombok por ejemplo para poder que el valor sea asignado a nueste varible de manera correcta*/
    @Value("${index.greeting}")
    private String greetinProperties;



    /*cabe recargar que el concepto de inyeccion de dependencias, hara que como nuestra clase de Controller
    * ya es un clase que esta disponible dentro de la fabrica de spring, entonces lo metodos de tipo, mapping
    * que estamos declarando podemos recibir distintos argumentos para nuestro parametros que definimos en
    * nuestros end-point simplemente  con declararlos, no tendreos que crear la instancia para dichos argumentos
    * sino que spring se encargara de crearla*/

    @GetMapping("*")
    public String index(Model model){
        String greeting = "hello world...";
        log.debug("ingresamos al end-point del index.estamos jecutando un controlador Sprign MVC");

        model.addAttribute("greeting", greeting);
        /*a travesz del este metodo pasamos informacion desde nuestro controlador Spring MVCh,
        ia nuestra vista que temos con thymeleaf*/

        model.addAttribute("properties", greetinProperties);
        return "index";
    }

}
