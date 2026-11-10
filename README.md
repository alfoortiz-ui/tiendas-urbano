# Tiendas Urbano

E-Commerce académico de moda femenina para demostración local con Spring Boot y Thymeleaf.

## Requisitos

- Java 21 (el `pom.xml` lo define; Java 27 también es compatible para la
  demostración local).
- Maven 3.9+.

## Ejecutar en local

```bash
mvn spring-boot:run
```

La aplicación quedará disponible en:

- `http://localhost:8080/`

El perfil `local` se activa por defecto y crea una base H2 local en
`./data/tiendas-urbano`. No requiere credenciales ni servicios externos.

## Ejecutar pruebas

```bash
mvn test
```

## Ver el frontend

1. Instala Java 21 y Maven 3.9+.
2. Desde la raíz ejecuta `mvn spring-boot:run`.
3. Abre `http://localhost:8080/` en el navegador.
4. Revisa `/catalog`, `/product`, `/login` y `/register`. El panel `/admin`
   requiere una cuenta con rol `ADMIN`; el registro público solo asigna
   `CLIENTE`, por lo que la administración de usuarios se incorporará en una
   iteración posterior.

Para detener la aplicación usa `Ctrl+C`. Para ejecutar con otro puerto:
`PORT=8081 mvn spring-boot:run`.

## Estructura base inicial

- `src/main/resources/templates/`: vistas Thymeleaf.
- `src/main/resources/templates/fragments/`: fragmentos reutilizables.
- `src/main/resources/static/css/`: estilos.
- `src/main/resources/static/js/`: scripts.
- `docs/ARQUITECTURA.md`: arquitectura, capas y contratos del MVP.
- `docs/PROMPT_SEMANA_1.md`: tareas, dependencias y criterios de la Semana 1.

Incluye:

- Página de inicio visual (`/`).
- Catálogo local filtrable y detalle de productos publicables.
- Registro con validación, BCrypt y rol `CLIENTE`.
- Login/logout con sesiones, CSRF y mensajes de error seguros.
- `/admin` protegido por autorización del servidor.
- Rutas aún pendientes de implementación: carrito, checkout, pedidos, pagos,
  despachos y reportes.
