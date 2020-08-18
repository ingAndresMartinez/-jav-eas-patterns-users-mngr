# jav-eas-patterns-users-mngr

### Pontificia Universidad Javeriana. Bogotá.

Manager para administración de usuarios que interactuan con la aplicación para al trabajo "socialización II" de la asignatura patrones 
de diseño, en la Especialización de Arquitectura de Software Empresarial 2020 I.

### Integrantes:

* Andres Martinez Cobos
* Fabian Acero
* Robinson Torres

* * *

### Recursos:

<table>
    <tr>
        <td>PATH</td>
        <td>DESCRIPCIÓN</td>
        <td>VERBO</td>
        <td>HTTP CODE OK</td>
        <td>HTTP CODES FAILED</td>
    </tr>
    <tr>
        <td>/user</td>
        <td>Permite la creación de nuevos usuarios</td>
        <td>POST</td>
        <td>201 - CREATED -</td>
        <td>406 - NOT_ACCEPTABLE - Creción invalida</td>
    </tr>
    <tr>
        <td>/user/login</td>
        <td>Permite el Login a la aplicación</td>
        <td>POST</td>
        <td>202 - ACCEPTED -</td>
        <td>403 - FORBIDDEN - Usuario Bloqueado <br>
            400 - BAD_REQUEST - Usuario o Clave Invalida</td>
    </tr>
    <tr>
        <td>/user/{userId}</td>
        <td>Permite la actualización de usuarios</td>
        <td>GET</td>
        <td>200 - OK -</td>
        <td>404 - NOT_FOUND - Consulta un cliente por Id</td>
    </tr>
    <tr>
        <td>/user/{userId}</td>
        <td>Permite la actualización de usuarios</td>
        <td>PUT</td>
        <td>202 - ACCEPTED -</td>
        <td>406 - NOT_ACCEPTABLE - Actualización invalida</td>
    </tr>
    <tr>
        <td>/user/status/{userId}</td>
        <td>Actualiza el estado de un cliente Activo - Inactivo o viceversa</td>
        <td>PUT</td>
        <td>202 - ACCEPTED -</td>
        <td>406 - NOT_ACCEPTABLE - Actualización invalida</td>
    </tr>
</table>
