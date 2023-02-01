### Curso de Spring

**clase-11-manejo-de-mensajes-spring-y-thymeleaf**

Mediante la creacion de un archivo de propiedades podemos darle valor a las equites que html que estamos manejando desde
nuestras templates que creamos con thymeleaf, podiendo darnos la oportunidad de externalizar el valor de estas etiquetas
para poder utlizar por lo tanto varios lenguajes para nuestra aplicacion spring mvc, sea un archivo de properties para 
definir por ejemplo los valores de esas etiquetas para Español, otro para English, etc...

Esto lo haremos aprovechando el manejo de mensajes de spring, donde vasicamente es la creacion de un archivo properties 
para definir los mensajes que queremos que se muesteen al momento de realizaar las validaciones con spring, para un DTO
o una Entity en cada uno de los atributos que necesitamos validar, en vez de que aparezca el mensaje por defecto que 
tienen cada una de la anotaciones de la dependencia de validacion de spring mostraremos nuestro propio mensaje 
personalizado, atrazes de la creacion del **messages.properties** el cual debera llmarse **messages** para poder que 
spring sepa que en este archivo properties manejaremos los messajes si le queremos colocar otro nombre tendriamos que
llevar una configuracion extra.

```properties
plantilla.tittle=Client Control
plantilla.footer=Derechos Reservados...
`````

y desde nuestra template invocariamos esos atributos defindos en el properties, mdeinate la sintaxis **#{}** y el nombre
para referenciarlo y traer su valor, ya sea mendiante las propias etiquetas de thymeleaf como **th:text="""** dentro 
de la etiqueta thml o apoyandonos de la sintaxis **[[#{}]]** para concatenar el valor de ese atributo x que traigamos del
properties von el texto que hallamos dado valor para nuestra etiqueta html

```html
<a th:href="@{/add}" th:text="#{person.create}"></a>
```

```html
<p>[[#{plantilla.footer}]]<a href="#">Juampis Montoya</a></p>
```
Para aplicar el manejo de mensajes conspring para nuestras anotsaciones de validaciones aplicadas, podemos configurarlas 
en el archivo properties que **denominamos messages para aplicar la configuracion por defaul de spring en el manejo de 
mensajes** de la siguiente manera; colocanto como propiedad el nombre de la anotacion concatenada
con el nombre de la clase mas el nombre del atributo al cual estamos cofigurando el mensaje de validacion
para cuando el valor que se le de a este  no sea valido
```properties
NotEmpty.person.name=The name attribute cannot be empty...
NotEmpty.person.lastName=The LastName attribute cannot be empty...
NotNull.person.phone=The phone attribute cannot be empty...
NotEmpty.person.email=The email attribute cannot be empty...
Email.person.email=The email attribute is not valid...
```