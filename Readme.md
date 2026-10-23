# 🍳 API REST - Gestión de Recetas y Categorías

## 🎯 1. Finalidad de la API
API REST desarrollada para administrar un sistema de cocina y recetas, permitiendo la gestión completa de categorías, ingredientes y recetas asociadas.

## 🛠️ 2. Tecnologías Utilizadas
- **Java 25** ☕
- **Spring Boot 4** 🍃 (Spring Web, Spring Data JPA, Validation)
- **Base de datos:** H2 / PostgreSQL 🗄️
- **OpenAPI 3 / Swagger UI** 📖
- **Lombok** 🛠️

## 🚀 3. Instrucciones de Ejecución
1. Clonar el repositorio: `git clone <https://github.com/DevJavierGomez/AD-y-PSP-Gestion-de-recetas-y-categorias>`
2. Acceder a la carpeta del proyecto: `cd gestion-recetas`
3. Ejecutar la aplicación con Maven: `./mvnw spring-boot:run`
4. Acceder a la documentación Swagger UI en el navegador: `http://localhost:8080/swagger-ui.html`

## 🧱 4. Entidades del Modelo
- **Categoria:** Identificador (`id`), `nombre` (único) y `descripcion`.
- **Receta:** Identificador (`id`), `nombre` (único), `tiempoPreparacionMin` y `dificultad` (`FACIL`, `MEDIA`, `DIFICIL`).
- **Ingrediente:** Identificador (`id`) y `nombre` (único).

## 🔗 5. Relaciones del Modelo
- **Categoría 1:N Receta:** Una categoría puede tener muchas recetas.
- **Receta N:M Ingrediente:** Una receta puede contener varios ingredientes y un ingrediente puede pertenecer a múltiples recetas.

## 📌 6. Tabla de Endpoints
| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `POST` | `/api/v1/categorias` | Crear categoría |
| `GET` | `/api/v1/categorias` | Listar categorías |
| `GET` | `/api/v1/categorias/{id}` | Buscar categoría por ID |
| `PUT` | `/api/v1/categorias/{id}` | Actualizar categoría |
| `DELETE` | `/api/v1/categorias/{id}` | Eliminar categoría |
| `POST` | `/api/v1/ingredientes` | Crear ingrediente |
| `GET` | `/api/v1/ingredientes` | Listar ingredientes |
| `POST` | `/api/v1/recetas/categorias/{categoriaId}` | Crear receta asociada a categoría |
| `GET` | `/api/v1/recetas` | Listar recetas |
| `GET` | `/api/v1/recetas/{recetaId}` | Obtener receta con sus ingredientes |
| `PUT` | `/api/v1/recetas/{id}` | Actualizar receta |
| `DELETE` | `/api/v1/recetas/{id}` | Eliminar receta |
| `POST` | `/api/v1/recetas/{recetaId}/ingredientes` | Asociar ingrediente a una receta |

## 📨 7. Ejemplo de Petición y Respuesta
**Petición:** `POST /api/v1/recetas/1/ingredientes`
```json
{
  "ingredienteId": 2
}
