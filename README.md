### Curso de Spring

**Clase-2-hello-world con thymeleaf, creacion de un controlador Sprign MVC, configuracion del banner de sprign,
y el no guardar cache de thymelaf en la configuracion del .properties, configuracion del name-space de thymeaf
en el arhivo html  y redirecionar desde el end-point hacia la template**

En esta clase vamos a utlizar la tecnologica Sprign MVC, para la creacion
de un monolito, donde la parte front, de nuestra palicacion la menejaremos dentro 
de muestro mismo projecto de spring mediante la tecnologia de thymeleaf, dado que es la 
mejor tecnologia para presentar informacion con spring en la creacion de un monolito, sin
necsidad de apoyarse en los viejos JSP.

* **El controllador**, el controlador no estaria con la anotacion @RestController sino que por
el contrario lo tendremos que convertir a un **controlador de tipo Spring MVC, con la utlizacion de 
la anotacion @Controller**, las ventajas que tenemos de trabajar con un controllador @RestController
o @Controller es que la configuracion es muy similar, por lo que de igual manera configuraremos nuestros 
end-point de igual manera.
* Para realizar el redicionamiento hacia la pagina, nuestro end-point tendra que retornar un string, 
que sera el nombre de la pagina a la cual queremos ir, para la cual con ayuda de la tecnologia de thymeleaf,
spring buscara un archivo html, dentro de la carpta templates en resources con el nombre que estamos
retornando en el string

* **Para que podamos manejar informacion dinamica** en nuestra template, el archivo html que estemos mostrando al cliente,
con ayuda de la tecnologia de thymeleaf,debemos de colocar dentro de la etiqueta html:
**xmlns:th="http://www.thymeleaf.org"**
y podremos hacer uso de las etiquetas de thymeleaf, por ejemplo:

  ![image](https://user-images.githubusercontent.com/62717509/206585543-7aad27fb-5314-4f94-88a3-875991d956af.png)


* **la tecnologia de thymelaf hace cache**, cabe mencionar que la tecnologia de thymeaf hace cahce por lo que debemos 
para mas comodidad quitar esto, con la configuracion que en el .properties, para que cuando estemos trabajando
no tengamos problemas al visulizar nuestra informacion en el navegador
**spring.thymeleaf.cache=false**