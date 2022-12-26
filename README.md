### Curso de Spring

**clase-8 CRUD**

Modificamos para la creacion ya de un CRUD completo, el create, read, update, delete,
de la clase domain "Person", atravez de la injection de dependencias de spring, en un end-point,
enviamos un objeto Person hacia el template en donde con ayuda de las etiquetas **th:object** asociamos ese objecto
a un formulario html, en donde tambien atravez de las etiquetas **th:field** en los input que tenemos para el
formulario podemos asociar los atributos de nuestro objeto Person al id de la etiqueta input y el valor que tenga
estos atributos a los valores del input, precargando la informacion en esto, y podiendo al momento de ingresar su respectivo
valor cambiarlo al del atributo del objeto Person, y enviar este objeto ya armado hacia otro end-point para persistirlo en la 
base de datos.

**con esta logica ya establecida, podemos reutilizar la template tanto para realizar un insert de un registro nuevo o para relizar un update de
un registro ya existente**, esto dado que cuando estamos dirigiendonos a esta template desde el end-point que envia el objeto person
con todos sus atributos nulos pues taremos modificandolos atributos de este, y enviadolos al repectivo end-point para que con ayuda
del service realiza un insert en la base de datos, y por otro lado al momento de que vengamos desde el end-point que envia un obejeto 
person con todos su datos completos, dado que lo consulto en la base de datos con su respectivo id, pues  estaremos modificando este
en la template y lo estaremos enviado con el mismo id con el que llego a la template, y enviandolo hacia el end-point que realiza el
insert en la base de datos, pero con ayuda de **JPA y la implmentacion de hibernate sera inteligente para saber que ese id esta 
ya en un registro de la base datos por lo que no relizara un insert sino que hara un update actulizando haci el registro con
la informacion que editamos desde la base de datos, esto porque por debajo se estara haciendo un select antes de realizar el inser
defindo en el metodo el repository para saber si ahy un registro ya la base de datos con ese id, de ser asi realizara un update**

Para el Delete, nos apoyamos practicamente de la misma logica denifinida en la template, para aculizar, en donde enviamos el atributo id
al end-point de nuestro controlador, el respctivo para realizar la eliminacion del resgistro en la base de datos,
con la diferencia que para eliminar lo enviamos por **Query Params (localhost:8080/delete/id?1)** donde en la template
con ayuda de la tecnologia de thymelaf se arma de la siguiente manera, 
```html
    <td><a th:href="@{/delete(id=${person.getId()})}" th:text="Delete"/></td>
``` 
dentro de una etiqueta td de una tabla de html, a diferencia de enviarlo por **path params (localhost:8080/delete/id/1)** como lo realizamos para el end-point
de actulizar, quedando de la siguiente manera para la template al momevento de anviarle el atributo al controlador y su end-point
```html
    <td><a th:href="@{/update/} + ${person.getId()}" th:text="Update"/></td>
```
en donde en el end-point difere uno del otro para recivir el atributo y setearlo al atributo Person
que obtengamos por injection de dependencia en el metodo del end-point que para Query params, solamente colocamos
el path por el que el end-point sera llamdo desde la vista y ya, automaticamente spring le asignara
el valor del atributo que nos envien desde el template a nuestro obejto a travez del metodo setter si este se llama
igual al del objeto, quedado algo como lo siguiente:
```java
     @GetMapping("/delete")
    public String dellete(Person person){
        this.service.deletePerson(person);
        return "redirect:/";
}
```
y para Path paramns tenemos que especificar el atributo que venga desde la template, de iguañl manera
spring le injectara este atributo al de nuestro objeto si se llman de igual manera
```java
     @GetMapping("/delete/{id}")
    public String dellete(Person person){
        this.service.deletePerson(person);
        return "redirect:/";
}
```