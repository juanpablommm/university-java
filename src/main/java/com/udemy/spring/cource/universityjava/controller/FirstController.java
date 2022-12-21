package com.udemy.spring.cource.universityjava.controller;


import com.udemy.spring.cource.universityjava.entity.Person;
import com.udemy.spring.cource.universityjava.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller("/")
public class FirstController {

    private IPersonService service;

    /*injectamos la dependencia por medio del controlador y no de la anotacion @Autowired*/
    public FirstController(IPersonService service) {
        this.service = service;
    }

    @GetMapping("*")
    public String index(Model model){
        log.debug("ingresamos al end-point del index.estamos jecutando un controlador Sprign MVC");
        List<Person> people = service.listPeople();
        model.addAttribute("people", people);
        return "index";
    }

}
