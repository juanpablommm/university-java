### Curso de Spring

**Clase 1- hello world, creacion de un primer controller, configuracion de puerto path, y nivel de loggin en el archivo .properties**

Al ir  a la pagina de sprint initializr o crear un projecto spring con el pulgins de este mismo desde el IDE, al selecionar la version de spring Boot que vamos autlizar, basicamente, spring, nos va a facilitar taresas como es la adminisracion de dependencias y administrar el servidor de palicaciones que ya viene embebido por ejemplo el tomcat que es e que viene por default, tambien por default las aplicaciones con spring Boot van a utilzar aplicaciones de tipos jar ya que para crear aplicaciones web con spring Boot ya no es necesario especificar que sean tipo war, sino que se trbaja con tipo jar.
Spring Boot ya agrega una cierta configuracion de librerias, lo cual hace la diferencia de trabajar con proyectos utilizando simplemente spring o trabajando con **spring Boot, pues spring boot ya agrega paquetes de librerias que podemos utilizar mas facilmente, como por ejemplo:**
* **Spring Boot DevTools** este nos permite reiniciar mas facilmente nuestro servidro de aplicaciones, y tambien utilizar LiveReload que nos permite refrescar nuestra aplicacion directamnte en el navegador...
* **Lombok** el cual nos facilita mucho el uso de los getters and setters, constructores, toString y demas...
* **Spring Web** la mas importante, ya que nos permite crear apicaciones de tipo RESt, y aplicaciones web usando MVC...
* **Thymeleaf** el cual nos permite agregar paginas web de manera mas simple a los JSP que se veiana en el pasado, esto para la creacion de monolitos...

**Spring Boot nos va a simplificar las creacion de aplicaciones con Spring dada los paquetes que nos permite para simplicar la configuracion, para que nos centralicemos en empezar a trbajar en la aplicacion...**

![image](https://user-images.githubusercontent.com/62717509/206083826-9ad1473c-ad86-44c6-aba4-be435f5d586e.png)

Para la carpta Resources se han creado dos carpetas adicionales, al estar trabajando con la tecnologia de Tyhmelaf para la creacion de un monolito, tenemos que la carpeta static nos permitra agregar elemntos staticos, como archivos js, css, o imagenes..
y para templates donde vamos agregar nuestros arhivos de presentacion utlizando thymelaf
Cabe recordar el .properties, para poder modificar las configuracion de nuestro framework de spring, y tambien algunas configuraciones de spring Boot, entres toras, como la conexion a la db, el puerto de la aplicacion un data-source, etc..

* Partes de las caracteristicas del framework de spring es que es un contenedor de clases java, asi que para que sprign reconosca las clases que vamos a agregar anuestra aplicacion deben estar dentro del mismo package donde se encuentr la clase Apllication, por ende si nos vamos asi la anotacion @SpringBootApplication y nos dirijimos asi la clase correspondiente podrmos observar que estan tendra una anotacion de @ComponentScan, la cual lo que va a hacer es bucar las clases que se encuenten dentro del package donde este la clase Application o en algun subpackage de este

En este clase creamos un primer controller para realizar un consumo a nuestra app, y obtener una respuesta...

**cabe recordar que el archivo .properties** es un archivo para la configuracion de nuestras aplicaciones de spring Boot
