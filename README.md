### Curso de Spring

**clase-6 spring data**

En esta clase se agregan las dependencias de spring data, y el driver de mysql conertor para trbajar con las bases de datos
de mysql, cuando agreguemos estas dependencias cabe recalcar que al momento de realizar un buil o correr nuestro projecto fallara
dado que la dependencia de spring data buscar automaticamente una configuracion para esta en el .properties y de no encontrarla 
fallara el build o el correr el projecto.

* cuando estamos utilizando jpa, spring por default, la implmentacion que va autlizar de jpa es hibernate, 
si vamos a utilizar otro tipo de implmentacion como eclipseLink open jpa o otro tipo,
debemos especificarlo en el arhivo de implementacion de dependencias (pom.xml para maven o gradle.build para gradle) y
otro tipo de condiguraciones para que funcione... ya no es necesario el archivo persisten xml de manera expicita 
todo esto lo administra y organiza de manera automatica, el tema de la unida de persietencia y que se va inyectar 
a los objetos de tipo dao, mucha de esta configuracion desaprece pues de manera automatica lo hace hace spring boot