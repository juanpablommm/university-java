### Curso de Spring

**clase-3 compartiendo informacion con spring MVC a thymelaf**
En esta clase se manda informacion desde nuestro controlador Spring MVC, hacia la template de thymeleaf
que estamos trabajando...
Normalmente cuando se trabajaba con la APi de los Servlets se tenia que para el end-point que se estuviera
trabajando, se tenia que tener un objeto HttpServletRequest y HttpServletResponse y manejar la informacion
guardandola en la seccion en la aplicacion, sin envargo
con spring no es necesario esto, para esto en el caso de nuestra aplicacion Spring MVC, al momento
de querer conmpartir HttpServletRequest infromacion con nuestra vista, que ya es una template con tymeleaf que seraia html puro y no jsp.
tendremos que apoyarnos de un Objecto **Model** como parametro den el end-point el cual nos servira
para llevar la informacion del controllador hacia la vista, mediante
la ayuda de los metodos **addAtribute** que este posee donde pasaremos
la informacion en pár de clave valor, colcando una clave para recuperar la finromacion que estamos enviando a la vista, y el
objecto que vamos a enviar, para ser recuperado atrabes de las etiquestas de thymeleaf, con esa clave que le dimos
desde el controlador, esto haciendo uso de **"${}"**

![image](https://user-images.githubusercontent.com/62717509/206933286-db74dfd9-3c18-4347-8cc2-f7d6ead9673b.png)

![image](https://user-images.githubusercontent.com/62717509/206933383-9305f089-6ff7-4197-80d3-43097a8a2061.png)

* ahora tambien podemos crear atributos en el properties para ser asignados a una varible, esto con ayuda
de la anotacion **@Value()** la cual le pasamos como parametro el el nombre del atributo como esta en el .properties,
con ayuda de la sintaxis **"${}"**

![image](https://user-images.githubusercontent.com/62717509/206933445-66cf3bf7-de05-4976-838b-f1625af33957.png)
