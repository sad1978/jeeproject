Puntos alcanzados en el proyecto de trello

SECCION CREAR MODELO DE DATOS
-Se deber�n considerar las tablas empresa, empleado y categor�a
R- Se han considerado tablas empresa y empleado
-Mapear tablas normalizadas en clases mediante Hibernate Tools
R- Hecho
-Mapear tablas no normalizadas mediante row Mapper
R- No hecho
-Realizar tests de integraci�n
R- Hecho para los servicios CRUD de las entidades empresa y empleado,
   para el servicio de aumento de sueldo en un porcentaje y
   para las API REST de empleado y empresa.

SECCION CREAR API DE SERVICIOS
-Crear un servicio por cada m�todo de CRUD de cada una de las entidades 
correspondientes a las tablas normalizadas (esto no incluye categor�as y subcategor�as)
R-Hecho
-Crear una consulta que permita obtener los datos de la empresa con mayor valor de liquidacion 
mensual
R-No hecho
-Crear un servicio que permita eliminar todos los datos de la base
R-No hecho
-Crear un servicio que permita establecer una variaci�n de sueldo en base a un porcentaje que se 
recibir� como par�metro
R-Hecho
-Crear un servicio que permita dar de alta una subcategor�a
R-No hecho

SECCION CREAR IMPLEMENTACION DE SERVICIOS
-Utilizar JPA Repositories en todos los casos en los que sea posible
R-Hecho

SECCION IMPLEMENTAR UN FRONTEND WEB
-Realizar una aplicaci�n Web que permita mantener (CRUD) todas las entidades
R- Hecho para empresa y empleado

SECCION IMPLEMENTAR UN FRONTEND DE SERVICIOS
-Ofrecer todos los servicios mediante una API REST
R-Hecho para los servicios CRUD de empresa y empleado y el servicio de aumento de sueldo.
-Ofrecer el servicio de aumento de salarios tambi�n mediante una API SOAP
R-Hecho

SECCION SECURIZAR APLICACIONES
-Securizar el frontend de servicios implementando una autenticaci�n mediante OAuth 
que acceda al usuario de Facebook
R-No hecho
-Securizar el frontend Web mediante Spring Security
R-Hecho
-Securizar los servicios, de forma que solamente puedan acceder a los servicios que 
realizan modificaciones aquellos usuarios que tengan perfil de administrador
R-Hecho

-Desarrollar un componente que publique autom�ticamente mediante una API REST HATEOAS, 
aquellos m�todos de los beans que contengan una determinada anotaci�n (Tanto la el bean 
como el m�todo)
R-No hecho

SECCION TESTING
-Tests unitarios (en cada proyecto)
R- Hecho en model y en service-frontend-soap
-Tests de integraci�n (en proyecto creado a esos efectos)
R- Hecho

SECCION CALIDAD
-En caso de que el c�digo contenga warnings o violaciones relevantes a reglas de PMD, 
FindBugs y CheckStyle, documentar la raz�n del mantenimiento de dicho c�digo.
R-No hecho
-Incluir javadoc de todos los m�todos de todas las interfaces creadas
R-No hecho
-Incluir documentaci�n en todas aquellas partes del c�digo que se considere necesario para 
su comprensi�n
R- Hecho en algunos sitios

SECCION TRAZAS
-Las trazas se generar�n utilizando la API slf4j y el framework logback
R-Hecho
-Se implementar� un aspecto mediante la clase CustomizableTraceInterceptor que genere 
trazas de todos los m�todos
R-No hecho

SECCION DEPENDENCIAS Y VERSIONADO
-Todos los n�meros de versi�n de las dependencias, deben gestionarse desde el parent POM
R-Hecho
-Para cada tarea que se pase a la columna Pending Corrections, se deber� generar un tag 
en GIT, y subir el �ltimo d�gito del n�mero de versi�n (0.0.1, 0.0.2, etc.)
R-Hecho

SECCION HTML
-Todas las p�ginas generadas deber�n estar validadas como HTML5 mediante http://validator.w3.org/
R-Hecho
