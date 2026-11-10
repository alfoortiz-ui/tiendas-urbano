# Arquitectura de Tiendas Urbano

## 1. Alcance de esta versión

Este documento define la arquitectura objetivo del MVP y el contrato de trabajo
para la Semana 1. Parte del estado actual de `main`, que contiene:

- `TiendasUrbanoApplication` como punto de entrada Spring Boot.
- `HomeController` con navegación básica hacia las plantillas Thymeleaf.
- Plantillas visuales en `src/main/resources/templates`.
- CSS y JavaScript en `src/main/resources/static`.
- Una prueba inicial de `HomeController`.

Las pantallas actuales de catálogo, producto, carrito, autenticación y
administración son placeholders. No deben considerarse funcionalidades de
negocio hasta que consuman servicios y datos persistidos.

## 2. Decisiones técnicas

- Java 21.
- Spring Boot y Spring MVC.
- Maven.
- Thymeleaf para renderizado del servidor.
- HTML, CSS y JavaScript mínimo para el navegador.
- Spring Data JPA/Hibernate para persistencia.
- Oracle como base local objetivo.
- Base embebida compatible solamente para pruebas automatizadas cuando Oracle
  no esté disponible.
- Spring Security para autenticación, sesiones, CSRF y autorización.
- Jakarta Bean Validation para validación de formularios y DTO.
- BCrypt para almacenar contraseñas.
- Pago y correo detrás de interfaces reemplazables, con implementaciones
  locales de demostración.

No se introducirá React, Node ni una segunda aplicación frontend en el MVP.

## 3. Estructura de paquetes

```text
src/main/java/com/tiendasurbano/
├── TiendasUrbanoApplication.java
├── config/
├── security/
├── controller/
│   ├── PublicController.java
│   ├── AuthController.java
│   ├── CatalogController.java
│   └── admin/
├── dto/
├── entity/
├── repository/
├── service/
├── validation/
├── audit/
├── payment/
├── notification/
└── exception/
```

La ruta `com.tiendasurbano` es el paquete raíz obligatorio. No se crearán
paquetes alternativos ni otro proyecto dentro del repositorio.

## 4. Responsabilidades por capa

### Controller

Recibe solicitudes HTTP, valida la entrada mediante DTO, delega en servicios y
devuelve vistas o respuestas. No calcula totales, cambia estados directamente
ni accede a repositorios.

### DTO

Representa los datos de entrada y salida de cada caso de uso. No se expondrán
entidades JPA directamente en formularios ni en vistas.

### Entity

Representa el modelo persistente y sus invariantes básicas. Las entidades no
deben contener llamadas HTTP, lógica de presentación ni acceso a otros
servicios.

### Repository

Define consultas y persistencia mediante Spring Data JPA. Las consultas
públicas deben devolver únicamente información publicable.

### Service

Contiene los casos de uso y reglas de negocio. Las operaciones que afecten
usuarios, estados, pagos o stock deben ejecutarse dentro de transacciones
cuando corresponda.

### Security

Configura autenticación, BCrypt, sesiones, CSRF y autorización del lado del
servidor. Ocultar un enlace en Thymeleaf nunca reemplaza esta capa.

### Payment y notification

Contienen contratos reemplazables. La Semana 1 solo debe definir los contratos
y el modo local necesario para probarlos; no se procesará dinero real ni se
guardarán tarjetas.

### Audit

Registra acciones administrativas mínimas: actor, acción, entidad, referencia y
fecha. Nunca registra contraseñas, tokens, secretos ni datos completos de pago.

## 5. Modelo inicial y relaciones

Durante la Semana 1 se preparan las entidades necesarias para autenticación y
catálogo:

```text
User ──< RoleAssignment >── Role
Product >── Category
```

El modelo completo del MVP se ampliará posteriormente:

```text
User ──< Cart ──< CartItem >── Product
User ──< Order ──< OrderItem >── Product
Order ──< Payment
Order ── Receipt
Order ── Shipment
Product ──< StockMovement
User ──< AuditEvent
```

Entidades planificadas:

`User`, `Role`, `Product`, `Category`, `Offer`, `Cart`, `CartItem`, `Order`,
`OrderItem`, `Payment`, `Receipt`, `StockMovement`, `Shipment`,
`NotificationLog` y `AuditEvent`.

La entidad `OrderItem` debe conservar nombre, SKU, precio, talla, color y
cantidad históricos. Los productos usados por pedidos históricos no se
eliminarán físicamente.

## 6. Roles y autorización

| Rol | Alcance |
|---|---|
| `CLIENTE` | Catálogo, cuenta, carrito y propios pedidos |
| `ADMIN` | Administración completa, usuarios, reportes y auditoría |
| `BODEGA` | Pedidos pagados, preparación, stock operativo y despachos |
| `CONTENIDO` | Ofertas, contenido y enlaces comerciales |

El registro público siempre crea `CLIENTE`. Solo un administrador autenticado
puede asignar roles privilegiados.

## 7. Contratos de la Semana 1

Los nombres siguientes son contratos compartidos y no deben cambiarse sin
actualizar este documento y avisar a los demás integrantes:

```text
UserRepository
RoleRepository
ProductRepository
CategoryRepository

UserService
AuthenticationService
CatalogService

RegisterUserRequest
LoginRequest
ProductResponse
CatalogFilter

PaymentGateway
NotificationSender
AuditService
```

Los métodos concretos se definirán en el PR de arquitectura antes de que otros
agentes implementen dependencias sobre ellos.

## 8. Rutas objetivo

Se conservan las rutas públicas ya existentes en `HomeController`:

```text
GET /
GET /catalog
GET /product
GET /cart
GET /login
GET /register
GET /admin
```

En la Semana 1 se deben ordenar por responsabilidad sin romper las vistas
existentes. Las rutas administrativas deberán quedar protegidas antes de
considerarse terminadas. Las rutas de negocio detalladas se acordarán en el
PR de arquitectura.

## 9. Reglas de integración

1. Cada integrante trabaja en una rama propia basada en `main`.
2. No se editan simultáneamente los mismos archivos centrales.
3. Las entidades, DTO y rutas compartidas se acuerdan antes de implementarse.
4. Cada PR incluye cambios, pruebas, requisitos cubiertos y pendientes.
5. La rama integradora ejecuta `mvn test` después de cada merge.
6. El Product Owner valida criterios funcionales y aprueba el PR.
7. El coordinador técnico valida arquitectura, conflictos y calidad de código.
8. No se agregan credenciales, datos reales ni servicios pagados.

## 10. Criterio de terminado de la Semana 1

La Semana 1 termina cuando:

- La aplicación conserva el inicio visual existente.
- La estructura de paquetes está creada y documentada.
- Java 21 está definido o la incompatibilidad del entorno está documentada.
- Las entidades y repositorios iniciales tienen contratos acordados.
- Existe una estrategia de configuración local y de pruebas.
- Registro, acceso, recuperación local, roles y autorización tienen diseño y
  tareas asignadas.
- Catálogo, búsqueda y detalle tienen DTO, servicios y repositorios definidos.
- Las plantillas ya no prometen operaciones que no estén conectadas.
- Los PR tienen pruebas y fueron revisados por arquitectura y Product Owner.

