package com.udemy.spring.cource.universityjava.service;


import com.udemy.spring.cource.universityjava.dao.IUserDao;
import com.udemy.spring.cource.universityjava.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsServiceJdbc")
/*dado que esta clase la va a utlizar spring security en el proceso de autenticacion,
el nombre de este Bean service debe de ser userDetailsService, no podemos utilizar otro
nombre ya que esto en automatico lo va a utilizar spring*/
public class UserServiceJdbcImpl implements IUserService{
    private IUserDao dao;

    public UserServiceJdbcImpl(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /*este metoodo es el mas importante para realizar el proceso de autenticacion
    * en donde como su nombre lo menciona va a obtener el susuario filfrado por un usrrname,
    * y terminamos regresando un objeto tipo UserDtails*/
    @Override
    // cabe recordar que los metodos de nuestras clase de servicio deben de ser transactionales, para poder realizarce
    //un roballck en caso de que sierta informacion de la operacion que se esta llevando contra la base de datos este corrompida,
    //realizando por ejemplo un resgitro o actulizacion completa de datos y no de unos cuantos, de ser asi se realiza un rollback,
    // y esa informacion no es percistida en la base de datos, evitando errores, para opera ciones de solo lectura, (readOnlny true)
    //en este caso como solo manejasmo lectura, no aplicamos un registro ni actulizacion en la base de datos, indicamos
    //que sera de solo lectura
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("this is an user null => " + username);
        }

        /*para que funcione spring securityt con la iplementacion de jpa que estamos utlizando debemos
        * envolver los roles que tenga nuestro usuario segun la base de datos, con el tipo de la interface
        * GrantedAuthority ya que esta es la que maneja srping security para manejar los roles y como savemos que
        * exite una relacion OneToMany entre User and Role pues creamos nuestra lista de tipo GrantedAuthority,
        * la cual debemos empezar a llenar con los roles que traigamos con ayuda de nuestro getter para el atributo
        * roles que definimos en nuestro entity el cual esta anotado con la anotacion OneToMany y JoinColumn
        * haciendo referencia hacia el atributo id_ser en nuestra tabla rol.
        *
        * para agregar un nuevo objeto GrantedAuthority a nuestra lista nos apoyamos de la creackon de un obejto
        * SimpleGrantedAuthority, en donde le pasmos por el contructor el nombre de nuestro rol recuperado*/
        List<GrantedAuthority> roles = new ArrayList<>();

        user.getRol().forEach(rol -> roles.add(new SimpleGrantedAuthority(rol.getName())));

        /*finalmente retornamos nuestro objeto UserDetails, con ayuda de su implementacion User, la cual pertenece
        * a la pacqueteria de spring security no es nuestro User entity, este user es el que utliza spring para
        * el proceso de autenticacion.
        *
        * A esta le pasmos en su constructor el nombre del susuario a autenticar en la app, el password y los roles que
        * tendra ete, informacion toda la que hemos obtenido de la base de datos*/
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasssword(), roles);
    }
}
