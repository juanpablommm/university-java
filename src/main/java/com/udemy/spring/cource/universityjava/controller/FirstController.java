package com.udemy.spring.cource.universityjava.controller;


import com.udemy.spring.cource.universityjava.dao.IPersonDao;

import com.udemy.spring.cource.universityjava.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Controller("/")
public class FirstController {

    private final IPersonDao iPersonDao;


    /*injectamos la dependencia por medio del controlador y no de la anotacion @Autowired*/
    public FirstController(IPersonDao iPersonDao) {
        this.iPersonDao = iPersonDao;
    }

    @GetMapping("*")
    public String index(Model model){
        log.debug("ingresamos al end-point del index.estamos jecutando un controlador Sprign MVC");
        Iterator<Person> iteratorPeople = iPersonDao.findAll().iterator();
        List<Person> people = new ArrayList<>();
        while (iteratorPeople.hasNext()){
            people.add(iteratorPeople.next());
        }
        model.addAttribute("people", people);
        return "index";
    }

}
