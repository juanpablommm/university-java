package com.udemy.spring.cource.universityjava.dao;

import com.udemy.spring.cource.universityjava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {


    /*
   Quey Methos
   Los QueryMtothods son la definicion de la consulta
   bacimante cpon el nombre de metodo, sin necesidad
   de escribir sentencias SQL como puede ser en el suso de
   JPQL, solamente necesitamos etablcer el nombre del
   metodo correctamente de acorde anuestra consulta,
   donde la primera parte del nombre del metodo sera un
   palabra clave para relizar el consulta, como por
   jemplo findBy que seria baciamente como su tracion
   encontrar por y la segunda parte del metodo seria
   el nombre o nombres del atributo para armar la sentencia
   como por ejemplo, findByName o FindByEmail o con la
   agregacion de otrasplabras reservadas como Or o And
   para consultas mas complejas ejemplo:
   findByNameAndEmail esto lo que hara es encontrar en
   la entidad que especificamos como tipo genrico para
   la JPARepostiry el atributo Name y Emial o cualquier
   otro que allamos establecido para el querymethod*/
    public abstract User findByUsername(String username);

}
