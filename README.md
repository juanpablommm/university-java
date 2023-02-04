### Curso de Spring

**clase-13-spring-security-Autenticacion**

Para agregar el concepto de seguridad a nuestra aplicacion web, lo primero que debemos hacer agregar la dependencia de 
seguridad a nuestra app
```implementation 'org.springframework.boot:spring-boot-starter-security'```
aunque existen mas temas para agregar seguridad, con la dependencia de **security** podemos empezar a agregar dicha 
seguridad, al implmentar esta dependencia automaticamente al correr la application se nos mostrara indlusive en el log
lo basico para empzar a utlizarla, dandonos un password, pues al observar la dentrar al host de la aplicacion 
observaremos como aparece pagina para hacer un loggin en la palicacion, donde el suario por defaul es user y el password 
el que se nos mostro por el log en la consola, de esta manera al ingresar estas  credenciales podremos acceder a nuestra
aplicacion.

* Ahora para poder personalizar los usuarios que pueden realizar loggin en nuestra applicacion, necesitamos crear un 
Bean de configuracion, en donde como ya sabemos al ser unBean de configuracion debemos agregar la anotacion 
````@Configuration```` tambien debemos agregar la anotacion ```@EnabledWebSecurity``` para poder habilidar la 
condiguracion de de la seguridad web con spring, al crear nuevos usuario se desactiva automaticament el usuario por 
default. 
Ahora bein para comenzar a personalizar nuestros suarios de la aplicacion debemos crear un metodo en nuestro bean de 
condifuracion el cual se llamara **configureGlobal** y estara anotado con un 
````@Autowired```` y debera de llevar como parametro un objecto de tipo ```AuthenticationManagerBuilder``` pues este es 
el que nos permitra modificar nuestros usuarios de la apalicacion, en este caso se crearan usuarios en memoria, para ello
llamamos al metodo ```whiUser("juan"")``` el cual recibe un string que sera el nombre de nuestro usuario, y para la 
contraseña mandaremos a llamar concatenadamente al metodo ````password()```` tambien recibe un string, el password debe de estar condificaco, lo cual se realizara posteriomente cuando se trabaje los usarios con la base de 
datos y finalamente tendremos que llamar al metodo ````roles("ADMIN"")```` en donde le pasaremos los roles que tendra nuestro 
usuario basicmanete spring le antepondra un "ROLE_" al rol por ejemplo "ROLE_ADMIN" y de **esta manera habremos creado 
nuestro usario para loguearnos a la aplicacion**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("juan")
                .password("{noop}060900")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("{noop},060900")
                .roles("USER");
    }

}
```
para la passwor al meter **{noop}** estamos mencionandole a spring que no queremos que la codifique, sino que la maneje 
como un texto plano

* Ahora para aplicar un **Logout** y que el suario salga de nuestra aplicacion y valla al login, vastaria con hacer una
petion post al path /logout que esta configuracion para salir ya nos la rovee spring, simplemente con realizar un post
hacia este path saldriamos de la apñlicacion al loggin,  para ende nostros lo metimos en nuestra plantilla de thymeleaf 
en la que estamos reulizando fragmentos, colocandolo el footer mediante un formulario que ralizara este post
```html
<footer th:fragment="footer">
    <div>
        <a th:href="@{/(lang=es)}">Spanish</a> |
        <a th:href="@{/(lang=en)}">English</a>
        <span>[[#{plantilla.footer}]]<a href="#">Juampis Montoya</a></span>
        <form method="post" th:action="@{/logout}">
            <a href="#" onclick="this.parentNode.submit()">Logout</a>
        </form>
    </div>
</footer>
```

Anteriomente nuestro Bean de configuracion que se encargaria de personalizar los usuarios que pueden acceder a la 
applicacion deberia de extender de la clase `````WebSecurityConfigurerAdapter````` y sobreescribir los metodos configurer
pero apartir de **spring 5** se decidio deprecar esta forma de accerlo por lo que esta clase ya ni se encuentra en los 
paquetedes de la dependencia de spring security