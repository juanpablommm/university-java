package com.udemy.spring.cource.universityjava.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*Creamos una clase controller para esto, donde lo primero que debemos realizar es anotarla @RestController,
     donde si nos vamos hacia esta clase, podemos observa que estara anotada con @Controller y asu vez al irnos
      hacia esa clase podremos obervar que estara anotada con @Component **pues esto es lo que le permite  a
      Spring reconocer esta clase como una clase de spring ya que las agrega al contenedor de Spring, si no anotammos
       una clase como o que es, @Controller, @Repository, @Service etc,  no seran agregadas al contenedor
     */

@RequestMapping("/inicio")
/*indicamos con la anotacion @RequestMapping cual sera el path para cuando se realice
 la peticon dentre a nuestro controllador, y de ahy con el resto del paht busque
  el end-point al cual dirijirse dependiendo del verbo*/

@Slf4j
/*para el maejo de logs esto nos lo pruebe la libreria de lombok*/
public record ControllerInicio() {


        @GetMapping("*")
    /*inicamos que este metodo, este end-point solo atrapara peticiones que vengan por el verbo GEt,
    * todas las que dentren a este controllador, por ende colocamos el **/
    public String inicio(){
        //resondemos al cliente con el siguiente string

            log.info("this is end-ponit for \"/inicio\" with verb GET");
            log.debug("this is a debug log for test config level logg in .properties file");
        return "hello world...";
    }
}
