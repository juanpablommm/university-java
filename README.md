### Curso de Spring

**clase-15-scguridad-desde-thymeleaf**


Se implmenta seguridad desde el lado de el motro de plantillas de thymeleaf, realizando desde el controlador que
nos lleva  ala template de la home page, la obtencion de los roles que hicieron del usuario que se autentico y pudo 
acceder al recurso segun las reglas de autorizacioln definidas desde el Bean de configuracion en para spring security,
desde nuestro controlador nos apoyamos de la inyecicion de dependencias de spring con un atributo de tipo
```Authentication``` de el core de spring security el cua, nos da la informacion del ausurio que se autentico en la app,
enviado haci atraves de nuestro ````Model```` a la template, el nombre del usuario, con ayuda del metodo ```getNname()``` 
y con ayuda del metoco ````user.getAuthorities()```` que nos devuelve una Collection mas el uso de las pia de stream la 
obtencion de una lista con todos los roles del susuario enviandole esa a la template y a la plnatilla que se se tiene
definada para nuestros codigos html que son comunes entre las templates, en donde nos mostramos el nombre del usuario 
que se autentico mas los roles de estes, y a nivel visual para que los suarios que no sean ADMIN no puedan ver los links
de los recuros para eliminar, actulizar y agregar, se utliza un ````th:if```` llamando
al objeto tipo lista que se envio desde nuestro controlador y llamndo verificando si tiene el rol de ADMIN, si lo tiene 
pues se renderiza la etiqueta html, de lo contrario no

```java
    @GetMapping("/")
    public String index(Model model, Authentication user){
        log.info("usaurio en el sistema => {}", user.getAuthorities()
        );
        List<String> roles = user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        log.info("usaurio en el sistema => {}", roles.contains("ROLE_ADMIN"));
        model.addAttribute("people", service.listPeople());
        model.addAttribute("username", user.getName());
        model.addAttribute("roles", roles);
        return "index";
    }
```

````html
<a th:if="${roles.contains('ROLE_ADMIN')}" th:href="@{/add}" th:text="#{person.create}"></a>
  <td th:if="${roles.contains('ROLE_ADMIN')}"><a th:href="@{/update/} + ${person.getId()}" th:text="#{action.update}"/></td>
````