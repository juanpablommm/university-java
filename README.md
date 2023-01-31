### Curso de Spring

**clase-9-validaciones-con-spring**

Desde nuestra clase domain, o entety como se le qquiera llamar, podemoes realizar validaciones para los atributos,
podiendo establcer si que se valide que no sean, nulo, o vacios para lo tipos string,que sean de tipo eamil por ejemplo
para desde el lado de la vista, de la template que estamos manejando con thymeleaf poder aplicar el codigo de verficacion
de si un atributo de tiene un error o nop, a la hora de realizar el registro y actulizacion, segun nuestro projecto...

para aplicar las validaciones con spring devemos de importar en nuestro projecto la dependencia de sping:
```java
 implementation 'org.springframework.boot:spring-boot-starter-validation'
```
y podremos hacer uso de lasa noatciones para la validaciones como
```java
@NotNull
@NotBlank
@NotEmpty
```

*Los validadores de spring los podemos utlizar para validar desde el lado del backen ciertos datos que vengan en una 
peticon hacia nuestro end-point de nuestro contolador, cuando viene un objeto json que sera transoformado a un DTO por
ejemplo o para validar entidades de dominio de igual forma tanto en un projecto psring mvc como un micro-servicio.
Desde nuestro controlador tendriamos que utlizar la anotacion ```@Validated``` para nuestro objecto que venga desde la
peticion junto con un paramtro de tipo **Errors** perteniciente al paquete de validaciones de spring
el cual nos permitira saber si ahy algun error, quedando algo como lo siguiente para el controlador
```java
@PostMapping("/save")
    public String save(@Validated Person person, Errors errors){
        if (errors.hasErrors()){
            return "update";
        }
        this.service.savePerson(person);
        return "redirect:/";
```
y en esete caos que estamos utlizando la tecnoglogia de thymeleaf, nos apoyamos de las condicionales
para dectar errors en el regitro y edicion de nuestro obejto person desde el formulario html,
usando asi atravez de un span si la condicional, condicional que se evalua mediante el comodin ```#fields.hasHerrors()```
para realizar el llamdo al metodo hasHerrors en donde validaremos pasandole como parametro el atributo de nuestra clase
que vallamos a validar si tiene un error para que no de un true o false si este presenta el error, se cumple el mostrar
el error en la template para el susario
```html
 <label th:for="*{lastName}">LastName:</label>
    <input  type="text" name="lasName"  th:field="*{lastName}">
    <span th:if="${#fields.hasErrors('lastName')}" th:errors="*{lastName}">Error LastName</span>
````