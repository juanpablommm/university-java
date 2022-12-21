package com.udemy.spring.cource.universityjava.service;

import com.udemy.spring.cource.universityjava.entity.Person;

import java.util.List;

public interface IPersonService {

    List<Person> listPeople();

    void savePerson(Person person);

    void deletePerson(Person person);

    Person findPerson(Person person);


}
