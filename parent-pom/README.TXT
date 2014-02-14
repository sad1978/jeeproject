Puntos alcanzados en el proyecto de trello

SECCION CREAR MODELO DE DATOS
-Se deberán considerar las tablas empresa, empleado y categoría
R- Se han considerado tablas empresa y empleado
-Mapear tablas normalizadas en clases mediante Hibernate Tools
R- Hecho
-Mapear tablas no normalizadas mediante row Mapper
R- No hecho
-Realizar tests de integración
R- Hecho para los servicios CRUD de las entidades empresa y empleado,
   para el servicio de aumento de sueldo en un porcentaje y
   para las API REST de empleado y empresa.

SECCION CREAR API DE SERVICIOS
-Crear un servicio por cada método de CRUD de cada una de las entidades 
correspondientes a las tablas normalizadas (esto no incluye categorías y subcategorías)
R-Hecho
-Crear una consulta que permita obtener los datos de la empresa con mayor valor de liquidacion 
mensual
R-No hecho
-Crear un servicio que permita eliminar todos los datos de la base
R-No hecho
-Crear un servicio que permita establecer una variación de sueldo en base a un porcentaje que se 
recibirá como parámetro
R-Hecho
-Crear un servicio que permita dar de alta una subcategoría
R-No hecho

SECCION CREAR IMPLEMENTACION DE SERVICIOS
-Utilizar JPA Repositories en todos los casos en los que sea posible
R-Hecho

SECCION IMPLEMENTAR UN FRONTEND WEB
-Realizar una aplicación Web que permita mantener (CRUD) todas las entidades
R- Hecho para empresa y empleado

SECCION IMPLEMENTAR UN FRONTEND DE SERVICIOS
-Ofrecer todos los servicios mediante una API REST
R-Hecho para los servicios CRUD de empresa y empleado y el servicio de aumento de sueldo.
-Ofrecer el servicio de aumento de salarios también mediante una API SOAP
R-Hecho

SECCION SECURIZAR APLICACIONES
-Securizar el frontend de servicios implementando una autenticación mediante OAuth 
que acceda al usuario de Facebook
R-No hecho
-Securizar el frontend Web mediante Spring Security
R-Hecho
-Securizar los servicios, de forma que solamente puedan acceder a los servicios que 
realizan modificaciones aquellos usuarios que tengan perfil de administrador
R-Hecho

-Desarrollar un componente que publique automáticamente mediante una API REST HATEOAS, 
aquellos métodos de los beans que contengan una determinada anotación (Tanto la el bean 
como el método)
R-No hecho

SECCION TESTING
-Tests unitarios (en cada proyecto)
R- Hecho en model y en service-frontend-soap
-Tests de integración (en proyecto creado a esos efectos)
R- Hecho

SECCION CALIDAD
-En caso de que el código contenga warnings o violaciones relevantes a reglas de PMD, 
FindBugs y CheckStyle, documentar la razón del mantenimiento de dicho código.
R-No hecho
-Incluir javadoc de todos los métodos de todas las interfaces creadas
R-No hecho
-Incluir documentación en todas aquellas partes del código que se considere necesario para 
su comprensión
R- Hecho en algunos sitios

SECCION TRAZAS
-Las trazas se generarán utilizando la API slf4j y el framework logback
R-Hecho
-Se implementará un aspecto mediante la clase CustomizableTraceInterceptor que genere 
trazas de todos los métodos
R-No hecho

SECCION DEPENDENCIAS Y VERSIONADO
-Todos los números de versión de las dependencias, deben gestionarse desde el parent POM
R-Hecho
-Para cada tarea que se pase a la columna Pending Corrections, se deberá generar un tag 
en GIT, y subir el último dígito del número de versión (0.0.1, 0.0.2, etc.)
R-Hecho

SECCION HTML
-Todas las páginas generadas deberán estar validadas como HTML5 mediante http://validator.w3.org/
R-Hecho
