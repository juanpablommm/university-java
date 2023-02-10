package com.udemy.spring.cource.universityjava.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


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
                .password("{noop}060900")
                .roles("USER");
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

}
