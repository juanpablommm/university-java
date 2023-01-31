### Curso de Spring

**clase-10-plantillas-con-thymeleaf**

Podemos aplicar un manejo de las plantillas con thymeleaft para reutilizar codigo html, para cuando tenemos varios 
elemtnos que simplemente se repiten, en donde simplmente podemos crear una pnatilla con thymeleaf
que sea reutilizable desde las demas templates, como para cuando tenemos un header, un footer o demas que sinplemente se 
pude reutlizar.

Para esta reutlizacion de codigo html con ayuda de la tecnologia de thymeleaf para nuestra aplicacion spring MVC, nos 
apoyamos de las etiquetas **th:fragment** del lado de la plnatilla que creamos para reutlizar
esta etiqueta va dentro de una html en donde le proporcionamos un nombre y todo lo que este dentro de la equita html
que la definomos con esta etiqueta de thymeleaft se podra reutilizar desde otra pagina html apoyandonos de la etiqueta 
**th:replace** desde la template desde la cual querramos reutlizar nuestro fragmento de codigo html que definimos dede 
nuestra plantilla colocando el path de esta sin la extencion .html mas el nombre del fragmento a traer
```html
<footer th:fragment="footer">
    <p>Derechos Reservados... <a href="#">Juampis Montoya</a></p>
</footer>
```

```html
<footer th:replace="layout/plantilla :: footer"></footer>
```