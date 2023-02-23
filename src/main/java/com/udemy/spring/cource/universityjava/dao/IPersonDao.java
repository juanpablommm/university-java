package com.udemy.spring.cource.universityjava.dao;

import com.udemy.spring.cource.universityjava.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonDao extends JpaRepository<Person, Long> {


}
