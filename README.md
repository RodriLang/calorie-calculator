# 🥗 Calorie Calculator API

Una API RESTful desarrollada con Spring Boot que permite gestionar alimentos y recetas, 
calculando su valor nutricional a partir de ingredientes, micronutrientes y unidades de medida.

---

## 🚀 Endpoints principales

### 🧾 Alimentos

- **GET** `/api/calorie-calculator/foods`: Listado de alimentos
- **POST** `/api/calorie-calculator/foods`: Crear alimento
- **PUT / PATCH** `/foods/{id}`: Modificar alimento
- **DELETE** `/foods/{id}`: Eliminar alimento
- **GET** `/foods/calories?min=0&max=300`: Filtrar por calorías
- **PUT** `/foods/{id}/micronutrients`: Agregar micronutrientes
- **PUT** `/foods/{id}/measurement-unit`: Agregar unidad de medida
- **PUT** `/foods/{id}/tags`: Agregar etiquetas

### 🧪 Micronutrientes

- **GET / POST / PUT / DELETE** `/micronutrients`: Gestión de micronutrientes

### 📋 Recetas

- **GET / POST / PUT / DELETE** `/api/sections`: CRUD de recetas
- **POST** `/sections/{id}/ingredients`: Agregar ingrediente
- **POST** `/sections/{id}/custom-ingredients`: Ingredientes personalizados
- **POST** `/sections/{id}/steps`: Agregar pasos a la receta

### 🍽️ Comidas

- **GET / POST / PUT / DELETE** `/api/calorie-calculator/meals`: CRUD de comidas
- **POST** `/meals/{id}/sections`: Asociar recetas

---

## 📦 Tecnologías

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Lombok
- OpenAPI (Swagger)
- Maven

---

## 📚 Documentación Swagger

Una vez levantada la aplicación, accede a la documentación interactiva en:

http://localhost:8080/swagger-ui.html


---

## 🛠️ Instrucciones de uso

1. Clonar el repositorio
2. Configurar la base de datos en `application.properties`
3. Ejecutar la app con:

```bash
./mvnw spring-boot:run

📌 Autor

Desarrollado por Rodrigo Lang - Ezequiel Santalla - Facundo Aguilera.