package com.udemy.spring.cource.universityjava.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {



    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("juan")
                .password("{noop}060900")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("{noop}060900")
                .roles("USER");
    }
    Cambiamos la autenticacion en memoria por la autenticacion con JDBC, en donde los usuarios que autenticaremos en la
    app con los que esten en nuestra base de datos*/


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*para configurar nuestra autenticacion de los usuarios de la app, ya con las implementaciones de JPA que definimos,
    * obteniendo los usuarios de la base de datos, nos podemos aportar de la creacion de un bean para AuthenticationManager,
    * en donde utlizamos la inyecion de dependencias para HttpSecurity, similar al proceso de autorizacion
    * en el que deifnimos un bean para SecurityFilterChain. y definimos cual sera nuestra impementacion de UserDetailsService,
    * con ayuda de la anotacion @Qualifier pasandole como parametro el nombre que le dimos a nuestro service, el cual
    * esta implementando nuestra interface service que definimos y que extiende de UserDetailsManage, que asu vez esta
    * extiende de UserDetailsService, siendo esta interface a la cual tenemos que definir la implemntacion del metodo
    * loadUserByUsername el cual utlizamos para traer el usuario de la base de datos con ayuda de nuestro dao y
    * retornamos un objeto tipo UserDetails, siendo en especial la clase User de spring security en el que decimos el
    * username del usuario, la password y el o los roles.
    * Una vez tenemos nuestra intancia a utilizar de UserDetailsService, la cual es la nuestra, al HttpSecurity,
    * le pasamos el obeto UserDetailsService mas el bean de encriptacion a tulizar, el cual es tipo BCryptPasswordEncoder
    */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, @Qualifier("userDetailsServiceJdbc") UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(this.bCryptPasswordEncoder())
                .and()
                .build();
    }

    /*la infraestructra de spring security esta basada en el standar de filtros de los servlets
    *
    * debemos crear un bean de SecurityFilterChain paara determinar que filtro srpring security sera aplicado a cada peticion.
    pueden haber varios SecurityFilterChain es parte de la arquitecura de spring security determinar cual de estos utilizar pa
      cada peticon basado en la url o en otro parametro
     */


    /*spring security para establecer niveles de autorizacion cuenta con 2 temas:
     *
     * 1, es definir el recurso al cual queremos acceder para ello este cuenta con los
     *  Matchers => dentro de los matcher tenemos:
     * *AnyRequest => como su nombre lo dice basicamente cualquier peticion puede ser considerada por este matcher
     * *RequestMachet => donde verificaremos que la peticon cunple con los requesitos url
     * *RequestMachert With HttpMethod = > es un varaiente de la anterior en donde podremos descriminar por el tipo de peticion http
     *
     * 2. una vez definamos que vamos a manejar la autorizacion de recurso o que cumple determinado criterio definido por los Matcher
     * debemos establcer las reglas de autorizacion
     * Autorization Rules => dentro de las reglas de autorizacion tenemos
     * *PermitAll
     * *DenyAll
     * *Athenticated
     * *hasRole
     * *hasAuthority
     * *Acces (spEL) Spring Espresion Lenguaje
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers("/update/**", "/add/**", "/delete/**", "/save/**")
                .hasRole("ADMIN")
                .requestMatchers("/")
                .authenticated()
                .requestMatchers("/login*", "/errores/unauthorized*")
                .permitAll()
                .and()
                /*poara definir cual sera nuestra pagina de login por defualt y no utilizar la spring security nos
                provee nos apoyamos de .formLogin().loginPage() en donde especificamos cual es la pagina que vamos a utlizar*/
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling()
                /*indicamos cual es el path a donde ira a buscar el recurso, la template que nos servira para mostrase
                * en caso de que halla un 403*/
                .accessDeniedPage("/errores/unauthorized")
                .and().build();
    }


    /*definimos un bean para nuestro tipo de encriptacion que vamos a manejar, siendo BCryptPasswordEncoder la recomendada
    por spring*/


}
