### Curso de Spring

**clase-7 manejo transacional, y creacion de la capa service**

* Para la implementacion de la cpa de servicio debemos crear una nueva interface, mas su implmentacion,
en la interface definimos los contractos, los metodos que vamos a crear a nivel de negocio, de servicio.
* En la implementacion deberemos anotarla con la anotacion @Service para indicar a Spring que reconozca esta clase como 
una clase de servicio, sino no hacemos eso depues no podremos injectar esta clase como una implmentacion de la interface
dentro del controlador. Spring la reconocera como parte del contenedor de Spring y la podramos inyectar en nuestro 
controlador.
En esta implmentacion, tendremos que inyectar nuestro DAO, o repositori con el que vamos a trabajar para la implmentacion
de nuestro metodos definidos en la interface servicies.

**debido a que estamos dentro de la cpa de servicio debemos agregar un tema mas...**
Cuando estamos dentro de la capa DAO se maneja el concepto de tranccasiones, esto quiere decir que cualquier operacon con
la base de datos, en dado caso de errror se va hacer un rollback, o  en dado caso de que todo este bien se realice
un commit, **pero cuando estamos trabajando con nuestras clases de servicio, podriamos estar utlizando varios obejectos 
de tipo DAO, desde una misma clase de servicio, por lo tanto podriamos estar utlizando varias operaciones, sobre distintas
tablas sobre la base de datos, por lo tanto tambien nuestros metodos en al capa de servicio debemos de marcarlos 
como trnzacionales, ya que en dado caso de errror debe de hacer un rollback, y no guardar la informacion en ninguna de 
las tablas afectadas, y en dado caso de que todo sea exito, debera de hacer un commit de toda la tranzacion, guardando 
toda la infromacion en todas la tablas afectadas.., ese concepto de tranzacional lo aplicamos dentro de nuestra 
implementacionde de la interface**
* Dependiendo del tipo de metodo es el dipo de trnsacion que vamos a utlizar, si nuestro metodo solo va leer informacion
podemos mecionar esto en la anotacon ```java @Transactional(readOnly = true)```
* En el caso de de guardar, eliminar o actulizar por ejemplo, si debemos colocar que sera transaccional sin ningun otro parametro
```java @Transactional``` ya que en este caso si se va a modificar la informacion en la base de datos por lo tanto
* si debera de hacer commit o rollback

**Nota:** Cuando trabajamos con la anotacion Transactional debemo de tener cuidado de inportar la correcta, esta debe de
pertenecer al paquete de spring


![image](https://user-images.githubusercontent.com/62717509/208219352-b6efbca7-012b-4a21-8280-e4f0aa786fcb.png)
Con esto ya tenemos todas nuestras cpas logicas, ya tenmos nuestra cap de datos incluyendo nuestra clase DAO,
nuestra inferca DAO y nuestra clase de monio, ya que estas son la encargadas de comunicarse con la base de datos.
Po otro lado ya tenemos nuestra cpa de negocion, implemntada con nuestros services,
y finalmente tenemo nnuestra capa de presentacion donde tenemos nuestro controlador de spring, y estmoas impmlentando
la presentacion utlizando la tecnoglocia de thymleaft