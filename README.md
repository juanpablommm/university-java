### Curso de Spring

**clase-16**

Se crean las tablas en la base de datos para el user y rol para el rol de nuestros usuarios,
aplicado una relacion oneToMany en donde **un usuario puede tener uno o muchos roles**,
y se utliza la codigicacion del atributo password en la tabla user, pues esge debe guardarse encryptado,
esto lo hacemos con lo que nos prove spring, el cual nos recomienda realizar las codificaciones con un objecto tipo
```BCryptPasswordEncoder``` para la autenticacion del usuario
```java
String password = "060900";
BCryptPasswordEncoder  bCryptPasswordEncoder = new BCryptPasswordEncoder();
log.info("060900 => {}", bCryptPasswordEncoder.encode(password));
```

Creamos nuestros entitys representativos de las tablas creadas en la base de datos para **user and rol**
en donde como vamos para cada una impmentamos serializable dado que estas clase estaran siendo trasportadas entre sockets,
en donde devemos implmentar tambien el atributo serializableVersionUID, para que cada objeto que sea pasado atraves de 
sockets no valla a tener inconvenitens despues con informacion de algun objeto mismo pasado anteriormete evitando estas 
molestias.
````java
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}

````

````java
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username")
    private String username;


    @NotEmpty
    @Column(name = "password")
    private String passsword;


    @OneToMany /*dato que en nuestra base de datos tenemos una realzacion deuno muchos
    en donde un usuario puede tener un o muchos roles, entonces nos apoyamos de la anotacion
    OneToMany indicado de uno a muchos.
    y con la anotacion JoinColum mencioamos el nombre del atributo de la entity con la que tenemos
     esa relacion, es decir nuestra foreng-ke.
     Nuestro atributo es un Lista de roles presisamente porque es una realcionOneToMany*/
    @JoinColumn(name = "id_user")
    private List<Rol> rol;
}

````
Una ves teniendo nuestra entitys repsentadoras de las tablas de usuario y rol en la base ded datos, no es que nosotros
vallamos a definir toda la funcionalidad de seguridad, spring lo que ha heccho para facilitarnos todo esto es que 
se han creado algunas clase las cuales nos van a permitir hacer mas facil esta configuracion como lohemos venido trabjando.

Ahora para nuestros repositorys o daos, tenemos que hacer que estiendadn de ````JpaReporitory```` interface que asu vez 
extiende de ````CrudRepository```` y ```PaginAndSortingRepository``` para el manejo de la paginacion en nuestras consultas
a la hora de haber muchos resitros en una tabla de nuestra base de datos.
en nuestro reporitory para User, aprovechamos q extendemos de JpaRepositroy y definimos un **QueryMethod** para 
endoncrear el susuario por el atributoUnsername, puesto que esa asi como no lo pide tambien Sprin Security.

* Los Query metodos no dan la facilida de aramar practicamente cualquier consulta que necesitemos de nuestra base de datos
por medio de la conjucacion de plabrabras claves en el nombre del metodo.
* findBy => al definir el nombre del metodo haci spring por debajo lo que hara es que ese metodo buscara por el atributo
que definamos de nuestra enityty por ende su traduccion tan literal "Encontrar por"
por ejemplo ````findByName```` => encontrar por namme, estaremos buscando el resgitro por el parametro name que le pasamos 
al metodo en su deifnicion.

````java
@Repository
public interface IUserDao extends JpaRepository<User, Long> {

    public abstract User findByUsername(String username);

}
````

* **Notal:** No olvidar que nuestros repositoris deben ir anotados con su respectiva anotacion ```@Repository``` tal y como lo
realzamos con los services, y nuestros entitys

  
Creamos nuestra interface service en la cual extendemos  de ````UserDetailsManager```` en lougar de ```UserDetailsService```
directamente pues extiende de esta ultima, pero nos provee de un par de metodos para la
administracion de los usuarios en nuestra app, metodos que nos permitiran la creacion de nuevos usuarios,
actulizarlos, eliminarlos, y comprobar si existen.
````java
public interface IUserService extends UserDetailsManager {

}
````

Y finalmente nuestra clase service que implmenta esta interface definira la implmentacion para el metodo ```loadUserByUsername```
de la interface ```UserDetailsService``` en donde es este el que nos permite realizar la configuracion para nuestros
usuarios autenticados en la app, pues como su nombre lo dice  obtiene el susuario filfrado por un username,
y terminamos regresando un objeto tipo UserDtails, de esta manera no aremos el proceso de autenticacion en nuestro bean
de configuracion refente a spring security **inMemoryAuthentication** sino que se estara realizando por medio de JDBC, 
utlizando nuestro service y dao.  

`````java
@Service("userDetailsServiceJdbc")
public class UserServiceImpl implements IUserService{
    private IUserDao dao;

    public UserServiceImpl(IUserDao dao) {
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

 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("this is an user null => " + username);
        }

       
        List<GrantedAuthority> roles = new ArrayList<>();

        user.getRol().forEach(rol -> roles.add(new SimpleGrantedAuthority(rol.getName())));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasssword(), roles);
    }
}
`````

Ahora para nosotros aplicar ya el proceso de autenticacion de los usuarios en la app, con nuestra implementacion de
````UserDetailsService```` simplemente cambiamos la configuracion que teniamos para realizar este proceso
de autenticacion en memoria, sino que aplicamos este con el que definimos nostros con la implmnetacion de JPA
para traer el usuario desde la base de datos.

````java
@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
    }


@Bean
public AuthenticationManager authenticationManager(HttpSecurity http, @Qualifier("userDetailsServiceJdbc") UserDetailsService userDetailsService)
        throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(this.bCryptPasswordEncoder())
            .and()
            .build();
}
````