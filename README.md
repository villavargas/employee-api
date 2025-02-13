Employee Management API

📌 Descripción

Este proyecto es una API REST desarrollada con Spring Boot, que permite la gestión de empleados, incluyendo operaciones CRUD.

🚀 Tecnologías Utilizadas

Java 17

Spring Boot 3.x

Spring Data JPA

H2 Database (base de datos en memoria)

Spring Validation

Springdoc OpenAPI (Swagger)

JUnit 5 y Mockito (pruebas unitarias)

Spring Boot Test y MockMvc (pruebas de integración)

📚 Estructura del Proyecto

/src/main/java/com/example/employees ├── controller/   
      # Controladores REST ├── service/          
	  # Lógica de negocio ├── repository/        
	  # Acceso a la base de datos ├── model/            
	  # Entidades JPA ├── exception/          
	  # Manejo de excepciones ├── config/       
      # Configuración (Swagger, etc.) /src/test/java/com/example/employees ├── controller/    
	  # Pruebas de integración ├── service/           
	  # Pruebas unitarias con Mockito

🔧 Instalación y Ejecución

1️⃣ Clonar el Repositorio

git clone https://github.com/lavv/employee-api.git
cd employee-api

2️⃣ Compilar y Construir el Proyecto

mvn clean install

3️⃣ Ejecutar la Aplicación

mvn spring-boot:run

La API estará disponible en:http://localhost:8080

📌 Endpoints Disponibles

1️⃣ Obtener todos los empleados

GET /api/employees

2️⃣ Obtener un empleado por ID

GET /api/employees/{id}

3️⃣ Crear múltiples empleados

POST /api/employees

4️⃣ Actualizar un empleado

PUT /api/employees/{id}

5️⃣ Eliminar un empleado

DELETE /api/employees/{id}

🛠 Base de Datos (H2)

Consola de H2:  http://localhost:8080/h2-console

JDBC URL: jdbc:h2:~/test

Usuario: sa

Contraseña: 

🗃️ Documentación con Swagger

Swagger UI: http://localhost:8080/swagger-ui.html

✅ Pruebas

Ejecutar Pruebas Unitarias y de Integración

mvn test


👨‍💻 Autor

Nombre: Luis Alejandro Villa Vargas
GitHub: @lavv

📚 Licencia

Este proyecto está bajo la licencia MIT. ¡Siéntete libre de usarlo y mejorarlo! 🚀