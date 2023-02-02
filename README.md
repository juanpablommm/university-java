### Curso de Spring

**clase-12-manejo-de-varios-idiomas-i18n**

El concepto de internacionalizacion o bien llamado el **i18n** basicamente es basicmaent el poder manejar varios idiomas
desde nuestra aplicacion, para nuestra aplicacion spring mvc, tenemos que configurar un listener el cual ya veine en 
spring donde unicamente tenemos que dentrar a configurarlo; en donde para ello tenemos que crear una clase de 
configuracion, **registrar la clase como un Bean de configuracion con la anotacion @Configuration de lo contrario 
ninguna de las configuraciones que efectuemos en esta clase para la aplicacion y la creacion de nuestros bean que 
necesitemos funcionaran**, la clase implementara la interface ```WebMvcConfigurer``` de 
```org.springframework.web.servlet.config.annotation.WebMvcConfigurer```
esta interfaces no provee de varios metodos que son importandotes como el ```addInterceptors()``` el cual puede utilizarse
para la pre y post personalizacion de llamadas a los end-point de un controlador.

Para nuestro caso en la creacion de nueestra clase de configuracion denominada **WebConfg** que implementa la interface
ya mencionada debemos crear un ```@Bean``` de la interface ```LocaleResolver``` perteneciente a 
```org.springframework.web.servlet.LocaleResolver``` el cual deolvera un objeto ```SessionLocaleResolver``` el cual 
es perteneciente a la misma libreria pero en el package podemos observar que esta l apalabra **i18n**
haciendo referencia a la internacionalizacion, parta la construcion de este Bean devemos de mandar a llamar el metodo
```setDefaultLocale()``` el cual recibe como parametro un objecto ```Locale``` que nos permitira establer el idioma que 
queremos que se maneje por default esto psandole un string en el contructor el cual es el pregfijo del idioma:

En la clase ````BaseLocal```` podemos encontrar los prefijos para los diferentes idiomas:
```java
baseLocales[ENGLISH] = createInstance("en", "");
            baseLocales[FRENCH] = createInstance("fr", "");
            baseLocales[GERMAN] = createInstance("de", "");
            baseLocales[ITALIAN] = createInstance("it", "");
            baseLocales[JAPANESE] = createInstance("ja", "");
            baseLocales[KOREAN] = createInstance("ko", "");
            baseLocales[CHINESE] = createInstance("zh", "");
            baseLocales[SIMPLIFIED_CHINESE] = createInstance("zh", "CN");
            baseLocales[TRADITIONAL_CHINESE] = createInstance("zh", "TW");
            baseLocales[FRANCE] = createInstance("fr", "FR");
            baseLocales[GERMANY] = createInstance("de", "DE");
            baseLocales[ITALY] = createInstance("it", "IT");
            baseLocales[JAPAN] = createInstance("ja", "JP");
            baseLocales[KOREA] = createInstance("ko", "KR");
            baseLocales[UK] = createInstance("en", "GB");
            baseLocales[US] = createInstance("en", "US");
            baseLocales[CANADA] = createInstance("en", "CA");
            baseLocales[CANADA_FRENCH] = createInstance("fr", "CA");
```

Tambien necesitamos ocnfigurar un Bean para el interceptor ```LocaleChangeInterceptor``` el cual nos permitira a travez
del metodo ```setParamName()``` establcer cual sera el nombre del parametro que nos permitra cambiar de lenguaje, en ese
caso conado adjutemos el parametro con el nombre que le indiquemos en nuestras URL podemos indicar cual sera el lenguaje
a implementar utlizando las sintaxis de internacionalizacion segun el pais que vallamos autlizar.

Despues de esto tendremos que agregar el interceptor con ayuda del metodo ```addInterceptors()``` que define la interface
que mencionamos antes

````java
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
}

````


y como paso final tenemos que agregar diferentes idiomas, por lo que crearemo dos archivos mas del **messages.properties**
en donde tenimoas los mensajes amostrar en nuestra template de thymeleaf con ayuda de sus etiquetas, y los mensajes
que configuramos para las validaciones de spring, donde simplmente estos archivos se llmaran de caorde a las siglas del 
lenguaje por ejemplo **messages_en.properties**, **messages_es.properties** y el defaul simplmente sera
**messages.properties** en dado caso que no se pueda reconocer o leeer el idioma entonces se toma la configuracion del 
archivo por default

ya en nuestra plnatilla que deifnimos en thymeleaf para ser reutilizadas por otras templates, aplicamos un link en el 
footer el cual envia al path principal de la palicacion por Query Param el nombre del atributo que definimos en nuestra
configuracion del SeessionLocalResolver con el idioma que vallamos a aplicar para nuestra aplicacion de acorde a los 
archivos messages.properties que tengamos para cada idioma
````html
<a th:href="@{/(lang=es)}">Spanish</a> |
<a th:href="@{/(lang=en)}">English</a>
<span>[[#{plantilla.footer}]]<a href="#">Juampis Montoya</a></span>
````