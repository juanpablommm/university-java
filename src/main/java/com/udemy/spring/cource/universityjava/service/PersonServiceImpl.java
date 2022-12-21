package com.udemy.spring.cource.universityjava.service;

import com.udemy.spring.cource.universityjava.dao.IPersonDao;
import com.udemy.spring.cource.universityjava.entity.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl  implements IPersonService{

    private IPersonDao dao;

    public PersonServiceImpl(IPersonDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listPeople() {
        return (List<Person>) dao.findAll();
    }

    @Override
    @Transactional
    public void savePerson(Person person) {
        dao.save(person);
    }

    @Override
    @Transactional
    public void deletePerson(Person person) {
        dao.delete(person);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPerson(Person person) {
        /*nos regresa un optional, este objecto lo que nos permite basicamente s decidir que
        * hacer en dado caso de que el valor esperado sea de tipo null, podemos seleccionar varias obsiones,
        * como que regrese solo el obejto, o en caso contrario que este no este decidamos que regresar, o en dado
        * caso que no este enviar una excepcion*/
        return dao.findById(person.getId()).orElse(null);
    }
}
