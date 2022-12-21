### Curso de Spring

**clase-6 spring data, configuracion de la conexion a la base de datos, con las dependencias de Spring Data JPA, mas el driver de MySQL,
y configuracion de nuestra clase dominio, mas nuestra clase repository, mostrar registros en la vista**

En esta clase se agregan las dependencias de spring data, y el driver de mysql conertor para trbajar con las bases de datos
de mysql, cuando agreguemos estas dependencias cabe recalcar que al momento de realizar un build o correr nuestro projecto fallara
dado que la dependencia de spring data buscar automaticamente una configuracion para esta en el .properties y de no encontrarla 
fallara el build o el correr el projecto.
 
* cuando estamos utilizando jpa, spring por default, la implmentacion que va autlizar de jpa es hibernate, 
si vamos a utilizar otro tipo de implmentacion como eclipseLink open jpa o otro tipo,
debemos especificarlo en el arhivo de implementacion de dependencias (pom.xml para maven o gradle.build para gradle) y
otro tipo de condiguraciones para que funcione... ya no es necesario el archivo persisten xml de manera expicita 
todo esto lo administra y organiza de manera automatica, el tema de la unida de persietencia y que se va inyectar 
a los objetos de tipo dao, mucha de esta configuracion desaprece pues de manera automatica lo hace hace spring boot

![image](https://user-images.githubusercontent.com/62717509/208219352-b6efbca7-012b-4a21-8280-e4f0aa786fcb.png)
Para los ejmplos que se han venido creando tenemos, una arquitectura multicapas,
en donde tenemos creado nuestro controlador el cual es parte del patron de diseño MVC,
donde estamos utilizando thymeleaf como tecnlogoia de presentacion, la vista es un archivo index.html, y el modelo es nuestra clase de Person por el momento
que hemos creado, donde deberemos crear mas adelante una capa de Negocio donde basicamente
agregaremos el concepto trancasional, y por ultimo tenemos la capa de datos, en la cual
vamos a crear clases de entidad como por ejemplo es nuestra clase Person, y utilizando JPA nos estamos conectando a la base dedatos,
ahora para que podamos recuperar informacion desde la vista y poder obtener la informacion vamos a utilizar los
repositoris de spring, anteriormente con utilizar la anotacion @Repository la cual era suficiente para crear este tipo de clases,
clases de tipo DAO, Data Access Objet y aunque se puede seguir utilizando este tipo de codigo, normalmente este era muy repetido,
por lo que al dia de hoy con el projecto de Spring data y utiliando el concepto de repositorio podemos ahorrarnos bastante codigo

**Por lo que en esta clase vamos a crear nuestro reporitory**
Anteriomrnte para crrear nuestros repositorys, debiamos aparte de agregar la anotacion
de @Repository, la cual deberiamos de agregarla sobre la inplmentacion, es decir sobre la clae
en concreta, debiamos crear primero una interfaces y despues crear una clase que seria su implmentacion
y en esta agregar la anotacion

Al dia de hoy esto ya se ha simplificado, ya no tenemos que agregar esta anotacion de manera directa,
sino que nuestra interface **CrudRepository**, y la ventaja es que cualquier clase de tipo DAO, vamos a poder crear utilizando
esta interfaces CrudRepository, al ingual que antes tambien debemos de especificar a esta interfaces, el tipo de clase de entidad que estamos
utlizando para representar a la tabla en la base de datos, y tambien especificar en el otro gnerico el tipo del atributo de esta clase dominio que representa
el id de dicha tabla en la base de datos , una de las grandes ventajas de utilizar esta practica es que no debemos crear una implmentacion
esto lo hara spring por default, al ir a ver la interface CrudRepository podremos ver los metodos mas comunes cuando
estamos trabajando con las clases de dentidad, pro ejemplo finAll que nos traera todos los registros, etc.. 