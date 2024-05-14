Proyecto Gestion Nomina SSN
Este proyecto es una aplicación de gestión de nómina que utiliza Spring Boot.

Realizado por:
Nestor Enoc Lerma
Joan Sebastian Barraza
Santiago Roa

Componentes del proyecto

1. Clase principal (GestionNominaSsnApplication.java): Esta clase es el punto de entrada de la aplicación. Se encarga de configurar e iniciar el servidor de aplicaciones.
2. Servicio de nómina (INominaService.java): Esta interfaz define los métodos necesarios para implementar las operaciones de nómina.
3. Implementación del servicio de nómina (NominaServiceImplementation.java): Esta clase implementa la interfaz INominaService y proporciona las implementaciones de los métodos definidos en la interfaz.
4. Configuración de la aplicación (application.properties): Este archivo contiene la configuración de la aplicación, como la URL base de la API REST, el puerto en el que se ejecuta el servidor de aplicaciones, etc.
5. Plantillas HTML (index.html): Este archivo contiene la plantilla HTML de la aplicación, que define la estructura y el diseño de la página principal.
6. Pruebas unitarias (GestionNominaSsnApplicationTests.java): Esta clase contiene pruebas unitarias para probar el correcto funcionamiento de la aplicación.

Instalación y ejecución del proyecto
Para instalar y ejecutar el proyecto, sigue estos pasos:

1. Descarga el repositorio del proyecto en tu computadora.
2. Abre el proyecto en tu IDE preferido.
3. Crear la base de datos "gestion_nomina_ssn" en MYSQL (Para el desarrollo de la API se utilizo xamp)
4. Ejecuta la clase principal (GestionNominaSsnApplication.java) para iniciar el servidor de aplicaciones.

	
Uso de la aplicación
Puedes utilizar la API REST proporcionada por la aplicación para realizar las siguientes operaciones:

1. Crear un nuevo empleado o Register:
   - Realiza en PostMan la creacion de un POST con la URL "http://localhost:8081/auth/register", ahora ve al body donde se creara un JSON el cual la siguiente estructura: 
	{
    "username":"Aqui va su correo",
    "password":"Aqui va su clave"
    "lastname":"Aqui va su Apellido"
    "firstname":"Aqui va su Nombre"
	}

2. Inicio de sesion o Login:
   - Realiza en PostMan la creacion de un POST con la URL "http://localhost:8081/auth/login", ahora ve al body donde se creara un JSON el cual la siguiente estructura: 
	{
    "username":"Aqui va su correo",
    "password":"Aqui va su clave"
	} 

3. Token de inicio:
   - En el paso anterior al finalizar y enviar el POST como resultado se obtuvo un Token el cual se utilizara en este paso, realiza en PostMan la creacion de un POST con la URL "http://localhost:8081/api/v1/Employee/list", ahora ve a Authorization donde seleccionaras "Bearer Token" y pondras el token que se optuvo en el paso anterior.


¡Para tener en cuenta!
   - Para poder acceder a los metodos CRUD es necesario el TOKEN que se obtuvo al iniciar sesion, de lo contrario no se podra acceder a estos metodos.



Listado de EndPoint's:

Registro: http://localhost:8081/auth/register
Login: http://localhost:8081/auth/login
Lista empleados: http://localhost:8081/api/v1/Employee/list
Generarpdf: http://localhost:8081/generatepdf
Actualizar usuario: http://localhost:8081/api/v1/2
Eliminar usuario: http://localhost:8081/api/v1/2


Nomina:

Crear nomina: http://localhost:8081/HumanResources/v1/Nomina/create
Listado nomina : http://localhost:8081/HumanResources/v1/Nomina/list
Actualizar nomina: http://localhost:8081/HumanResources/v1/1
Eliminar nomina: http://localhost:8081/HumanResources/v1/202

Request de nomina:

{
  "salary": 20000.0,
  "paymentDate": "2024-03-31",
  "id_employee": {
    "id": 1,
    "username": "nestorlerma1609@gmail.com",
    "firstname": "Joan",
    "lastname": "Barraza",
    "email": "nestorlerma1609@gmail.com",
    "password": "$2a$10$ohw7AMcPq7vP4tB/Dwjt0.x5IRvQSykd/1e9dpOJ.aJWojQYQ6XWK",
    "role": "ADMIN"
  }
}
