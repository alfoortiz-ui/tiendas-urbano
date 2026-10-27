# Tiendas Urbano

E-Commerce académico de moda femenina para demostración local con Spring Boot y Thymeleaf.

## Requisitos

- Java 21 recomendado para el proyecto (en este entorno se validó con Java 17).
- Maven 3.9+.

## Ejecutar en local

```bash
mvn spring-boot:run
```

La aplicación quedará disponible en:

- `http://localhost:8080/`

## Ejecutar pruebas

```bash
mvn test
```

## Estructura base inicial

- `src/main/resources/templates/`: vistas Thymeleaf.
- `src/main/resources/templates/fragments/`: fragmentos reutilizables.
- `src/main/resources/static/css/`: estilos.
- `src/main/resources/static/js/`: scripts.

Incluye:

- Página de inicio visual (`/`).
- Rutas placeholder: `/catalog`, `/product`, `/cart`, `/login`, `/register`, `/admin`.
