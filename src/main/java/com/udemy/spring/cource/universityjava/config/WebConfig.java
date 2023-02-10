package com.udemy.spring.cource.universityjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("en"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.localeChangeInterceptor());
    }

    /*al sobreescribir este metodo en nuestro BEan de configuracion,
    * este metodo nos permitira mapear peticiones que no tienen un end-point en un controllador para
    * que este las redireciones hacia las vistas que necesitamos*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*como no tenemos un end-point en un controllador que coja la peticion
        * de login y nos redirecione hacia la vista personalizada que creamos, entonces
        * registrmos la vista para que nos redirecione hacia nuestra template, sin necesidad de pasar por un
        * controllador
        *
        * con la soobreescritura del metodo addViewController agregamos un controllador de vista que
        *  coo ya se menciono nos servira para
        mapear peticion que no tiene un conntrollador asocciado, donde nos devuleve un objeto ViewControllerRegistration
        * al cual usaremos el metodo setViewNeame que nos permitira pasarle nombre de nombre de donde esta nuestra
        * vista a la cual se hara la redirecion.
        * Nota: cuando se tien un controlador que capture la peticion no se debe utilizar un controlaador de vista dado que
        * estos puede causar conflictos */
       registry.addViewController("/login").setViewName("login");
       registry.addViewController("/errores/unauthorized").setViewName("errores/unauthorized");
    }
}
