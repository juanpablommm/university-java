package com.udemy.spring.cource.universityjava.dao;

import com.udemy.spring.cource.universityjava.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface IPersonDao extends CrudRepository<Person, Long> {


}
