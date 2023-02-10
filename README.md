### Curso de Spring

**clase-14-spring-security-Autorizacion**

Para el proceso de restringr las URL de nuestra aplicadcion Spring MVC en este caso se le conoce como Autorizacion,
miesntras el proceso de defirnir o personlizar nuestros usiuarios de la aplicacion visto en la clase anterior se le 
conoce como Autenticacion.

Para la Autorizacion empezarmos a restringir las URL de nuestra aplicacion en base a los Roles que definomos para los 
usuario que se crearon en memoria para la app.

Para ello necesitamos hacer uso de la clase ````HttpSecurity```` perteneciente a la misma paqueteria de la misma 
dependencia de spring security, donde con ayuda los metodos como; ```authorizeHttpRequests()``` mencionamos que vamos a 
empezar a relizar las configuraciones de nuestros **matchers y nuestras reglas de autorizacion**
para empezar la configuracion de las url a las cuales se pueden acceder dependiendo del rol, authorites, autenticacion 
y demas reglas de autorizacion, en donde para ello necesitamos configurar  nuestro Bean ````SecurityFilterChain````.

La infraestructra de spring security esta basada en el standar de filtros de los servlets , 
debemos crear un bean de SecurityFilterChain paara determinar que filtro srpring security sera aplicado a cada peticion.
pueden haber varios SecurityFilterChain es parte de la arquitectura de spring security determinar cual de estos utilizar
para cada peticon basado en la url o en otro parametro.

Por un lado tenemos los **Matchers** => definiendo el recurso o end-point al cuale se quiere acceder, entre estos tenemos:
* anyRequest => como su nombre lo dice basicamente cualquier peticion puede ser considerada por este matcher
* requestMatchers => donde verificaremos que la peticon cunple con los requesitos url
* RequestMachert With HttpMethod = > es un varaiente de la anterior en donde podremos descriminar por el tipo de peticion http

Por otro lado tenemos las **reglas de autorizacion* => donde una vez definamos que vamos a manejar la autorizacion
de recurso que cumple determinado criterio definido por los Matchers, procedemos aplicar recglas de autorizacion
para determinar que usuarios pueden acceder a estos recursos, entre estas tenemos:
* debemos establcer las reglas de autorizacion s
* permitAll => permite que todos tengan acceso al recuros sea un usuario con determinado rol, o este autenticado en la 
aplicacion o nop.
* denyAll => el contrario que a anterior, deniega a todos el accesos hacia el recuros, haci sea un usuario admin autenticado 
* authenticated => para acceder al recurso se tiene que estar autenticado en la aplicacion
* hasRole => solo se puede acceder al recurso los usuarios que tengan determinado rol
* hasAuthority => solo pueden acceder al recuro los usarios que tenga determinado authorities, (write, read)
* Acces (spEL) Spring Espresion Lenguaje => cuando no poemos especificar la regla de autorizacion con las demas usilizamos 
la ayuda de acces mediante spring expresion lenguaje

ya sabiendo esto podemos empezar a denar y dar accesos a nuestros recursos comobiando estas; por ejemplo:
````java
//solo los usuarios con rol ADMIN puende acceder
.authorizeHttpRequests()
.requestMatchers("/update/")
.hasRole("ADMIN")

//todos pueden acceder al recurso
.requestMatchers("/login*")
.permitAll()

//si exiten dos end-points iguales pero con diferente verbo, podemos retringirlos de acorde a eso
.requestMatchers(HttpMethod.GET,"/add").authenticated()//solo usuarios autenticados pueden acceder a este end-point
.requestMatchers(HttpMethod.POST, "/add").hasRole("ADMIN")//solo usuario admin pueden acceder
````

para nuestra aplicacion definimos las reglas de autorizacion:
````java
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
````

al momento de que establezcamos nuestra propia vista para el loggin debemos tener en cuenta
que la peticion para que ocurra el procesos de autenticacion debe ir extrictamente hacia ```/login``` por lo 
que el action de nuestro formulario html de ser hacia este, y los name de los input del fomrulario
tambien deben de ser extrcitamente ```username y password``` dado que estos son los nombre de los atributos
que espera sprin security para ralizar el procesos de autenticacion
```html
<!DOCTYPE html>
<html lang="es" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="post" th:action="@{/login}">
  <label for="username">Username:</label>
  <input type="text" name="username" id="username">

  <label for="password">Password:</label>
  <input type="password" name="password" id="password">
  <input type="submit" value="login">
</form>
</body>
</html>
````

* para el caso de que configuremos cual una pagina personalizada
para mostrar cuando halla un error 403 en una peticion  hacia alguno de nuestros 
recursos como se configuro en el Bean del **SecurityFilterChan** inyectando HttpSecurity
donde podemos definir cual sera el path en el que se busacara la pagiana amostrar por
default cunado surja un error 403, debemos maepar eese path hacia donde se encuentra 
la template a realizar el redireccionamiento, esto con un controllador de vista, el cual 
simplmente en nuestro bean de configuracion donde implmentamos ````WebMvcConfigurer````
sobreescriviriamos el metodo ```addViewControllers``` el cual recibe como parametro un obejto
````ViewControllerRegistry```` que nos permirira agregar un controlador de vista en donde llamaremos el metodo
````addViewControllers```` para inciar el path sobre el cual mapeara la petcion al  no tener un controler,
en este tenemos dos controladores de vista para mapear sobre el path /login y /errores/unauthorized los cuales deinfimos
para el securityFilterChain, el login y el path a donde buscar la pagina que establecimos por defaul
para cuando halla un error 403, y con ayuda del metodo ````setViewName```` finamente le damos el path de la template
sin la estension html, en este caso nuestra pagina de login esta en la carpta templates por lo que hira 
a buscarla ahy, y como la tecnologia de thymelaf para que busque una template no ahy que 
colocarle esa caprta en el path, solo basta con el nombre del archivo, con el nombre login, el archivo login.html y para la pagina del error 403 en un subcarpta
errores dentro de templates,
````java
   @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/login").setViewName("login");
       registry.addViewController("/errores/unauthorized").setViewName("errores/unauthorized");

````
* finalmente si desde un end-point quermos capturar el usuario que se autentico para acceder a ese recuros
segun la regla  de autorizacion que hallamos defino podemos uslizar la anotacion y el attributo 
```@AuthenticationPrincipal User user``` en el metodo de nuestro end-pointal cual accedio el cleinte,
esto nos permitira saber cual fue le suario que se autentico para acceder a nuestro recurso
