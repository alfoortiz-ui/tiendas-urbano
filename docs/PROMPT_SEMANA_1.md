# Prompt de ejecución — Semana 1

## Contexto obligatorio

Trabajas sobre el repositorio `alfoortiz-ui/tiendas-urbano`, rama base
`main`. Lee primero:

1. `Especificacion_Desarrollo_Urbano_Ecommerce.md`.
2. `docs/ARQUITECTURA.md`.
3. `README.md`.
4. `pom.xml`.
5. `src/main/java/com/tiendasurbano/controller/HomeController.java`.
6. Las plantillas actuales en `src/main/resources/templates`.

El código de `main` es una base visual Spring Boot + Thymeleaf. No asumas que
una pantalla placeholder implementa una operación real. Conserva lo válido y
realiza cambios incrementales.

## Objetivo de la Semana 1

Construir las fundaciones seguras del MVP para HU-01 Registro, HU-02 Acceso,
HU-03 Catálogo y HU-15 Roles y auditoría. La salida debe ser una estructura
POO mantenible, con contratos compartidos, configuración local y trabajo
dividido en PR revisables. No implementes todavía carrito, checkout, pedidos,
pago, comprobante, despachos ni reportes.

## Reglas para todos los integrantes

- Crea una rama propia desde `main`.
- Lee la arquitectura antes de editar.
- No cambies nombres de entidades, DTO, servicios o rutas compartidas sin
  actualizar `docs/ARQUITECTURA.md`.
- No pongas lógica de negocio en controladores ni JavaScript.
- No guardes contraseñas en texto plano.
- No agregues roles privilegiados desde el registro público.
- No uses datos personales reales, credenciales ni secretos.
- Ejecuta las pruebas específicas y `mvn test` antes de abrir el PR.
- El PR debe informar archivos cambiados, pruebas, requisitos cubiertos,
  supuestos y pendientes.

## Integrante 1 — Product Owner

### Objetivo

Convertir la especificación en criterios verificables y aprobar los PR de la
Semana 1.

### Tareas

1. Revisar HU-01, HU-02, HU-03 y HU-15.
2. Confirmar que el registro solicite nombre, RUT, correo, teléfono y
   contraseña.
3. Confirmar que toda cuenta pública nazca como `CLIENTE`.
4. Confirmar que login y recuperación local tengan mensajes seguros.
5. Confirmar que catálogo solo muestre productos activos y publicables.
6. Confirmar que la clienta no vea costos internos ni notas de bodega.
7. Confirmar la matriz de permisos de `CLIENTE`, `ADMIN`, `BODEGA` y
   `CONTENIDO`.
8. Crear una checklist de aceptación para cada HU.
9. Revisar visualmente el PR de interfaz.
10. Aprobar o rechazar los PR de los demás integrantes cuando cumplan los
    criterios funcionales.

### Entregable

Checklist de aceptación de HU-01, HU-02, HU-03 y HU-15, con observaciones y
aprobaciones registradas en los PR.

## Integrante 2 — Arquitecto/coordinador técnico

### Objetivo

Dejar lista la estructura base y los contratos que usarán los demás agentes.

### Tareas

1. Crear y mantener `docs/ARQUITECTURA.md`.
2. Actualizar `pom.xml` hacia Java 21 si el entorno lo permite; si no,
   documentar la limitación sin cambiar el objetivo del proyecto.
3. Preparar paquetes `config`, `security`, `controller`, `dto`, `entity`,
   `repository`, `service`, `validation`, `audit`, `payment`, `notification`
   y `exception`.
4. Configurar perfiles local y test sin secretos en Git.
5. Definir contratos de `User`, `Role`, `Product` y `Category`.
6. Definir los estados de usuario y publicación de producto.
7. Definir `UserRepository`, `RoleRepository`, `ProductRepository` y
   `CategoryRepository`.
8. Definir DTO de registro, login, filtros de catálogo y respuesta de producto.
9. Separar navegación pública de administración sin romper las rutas actuales.
10. Revisar los PR antes de que el Product Owner los apruebe.

### Entregable

PR de arquitectura con estructura, contratos, configuración y documentación.

### Criterios de aceptación

- `main` sigue iniciando.
- La arquitectura no crea paquetes duplicados.
- Las capas tienen responsabilidades claras.
- Los contratos son utilizables por seguridad y catálogo.
- `mvn test` pasa.

## Integrante 3 — Seguridad y autenticación

### Objetivo

Preparar la base de HU-01, HU-02 y HU-15 con seguridad en el servidor.

### Tareas

1. Implementar el modelo de usuario y roles según la arquitectura.
2. Implementar DTO y validación backend del registro.
3. Crear usuarios con rol `CLIENTE` exclusivamente.
4. Configurar BCrypt.
5. Preparar Spring Security para sesiones y CSRF.
6. Proteger `/admin` y futuras rutas privadas.
7. Implementar login y logout.
8. Diseñar recuperación local mediante token temporal o bandeja local.
9. Evitar mensajes que revelen si un correo existe.
10. Crear pruebas de registro, login, contraseña incorrecta y acceso sin rol.
11. Preparar el contrato de auditoría sin registrar secretos.

### Entregable

PR de autenticación y autorización con pruebas.

### Criterios de aceptación

- No se guarda ni imprime la contraseña original.
- El registro no acepta `ADMIN`, `BODEGA` ni `CONTENIDO`.
- Una ruta privada no funciona sin autenticación/autorización.
- El login fallido devuelve un mensaje seguro.
- Las pruebas de seguridad pasan.

## Integrante 4 — Catálogo y dominio

### Objetivo

Preparar HU-03 con datos persistentes y reglas de publicación.

### Tareas

1. Implementar `Product` y `Category` según los contratos.
2. Crear repositorios para productos activos y publicables.
3. Implementar `CatalogService`.
4. Implementar búsqueda por texto.
5. Implementar filtros por categoría, talla, color y disponibilidad cuando el
   modelo los soporte.
6. Crear `ProductResponse` sin costo de importación, margen ni notas privadas.
7. Preparar detalle de producto.
8. Crear datos ficticios de catálogo para desarrollo y pruebas.
9. Crear pruebas de producto activo, inactivo, búsqueda y filtros.
10. Coordinar con interfaz las rutas y nombres de atributos.

### Entregable

PR de catálogo con repositorios, servicio, DTO, datos ficticios y pruebas.

### Criterios de aceptación

- Solo se publican productos activos.
- La respuesta contiene nombre, descripción, precio, categoría, talla, color
  y disponibilidad.
- La búsqueda no expone datos internos.
- Las pruebas del servicio pasan.

## Integrante 5 — Interfaz y accesibilidad

### Objetivo

Organizar las vistas actuales para consumir los contratos reales sin prometer
funciones inexistentes.

### Tareas

1. Revisar `HomeController` y conservar la navegación que ya funciona.
2. Organizar plantillas en áreas públicas, autenticación, cuenta y
   administración sin romper Thymeleaf.
3. Reemplazar placeholders genéricos por textos honestos de estado pendiente
   cuando el backend aún no esté conectado.
4. Preparar formularios de registro y login con etiquetas, errores y estados
   accesibles.
5. Preparar catálogo y detalle para consumir `ProductResponse`.
6. Mantener fragmentos compartidos de header, topbar y footer.
7. Revisar navegación por teclado, contraste, texto alternativo y `aria-live`.
8. Eliminar acciones JavaScript que simulen operaciones backend no existentes.
9. No implementar permisos ocultando botones.
10. Crear o actualizar pruebas de renderizado de las vistas principales.

### Entregable

PR de interfaz con vistas organizadas, accesibles y alineadas con las rutas y
DTO acordados.

### Criterios de aceptación

- Las páginas actuales continúan cargando.
- No hay botones que aparenten guardar datos sin backend.
- Los formularios tienen etiquetas y mensajes claros.
- La interfaz funciona en escritorio y móvil.

## Integrante 6 — QA y documentación

### Objetivo

Verificar que la base de la Semana 1 sea integrable y trazable.

### Tareas

1. Revisar que cada PR parta desde `main` actualizado.
2. Crear una matriz HU → requisito → prueba → resultado.
3. Ejecutar `mvn test` después de cada integración.
4. Probar inicio, catálogo y rutas actuales.
5. Probar rutas privadas sin autenticación.
6. Revisar ausencia de secretos y contraseñas en código y logs.
7. Documentar fallos bloqueantes y menores.
8. Revisar que README explique Java, Maven y ejecución local.
9. Confirmar que los PR incluyan pruebas, supuestos y pendientes.
10. Emitir informe de calidad para la aprobación del Product Owner.

### Entregable

PR de pruebas/documentación y matriz de aceptación de la Semana 1.

## Orden de trabajo y dependencias

1. Arquitectura crea estructura y contratos.
2. QA verifica que la base compile.
3. Seguridad implementa usuario, roles y autenticación.
4. Catálogo implementa productos, repositorios y servicio.
5. Interfaz conecta formularios, catálogo y estados.
6. QA ejecuta regresión.
7. Product Owner valida y aprueba cada PR.
8. Arquitectura integra únicamente PR aprobados y ejecuta `mvn test`.

## Formato obligatorio de cada PR

```text
## Objetivo

## Requisitos/HU cubiertos

## Archivos modificados

## Pruebas ejecutadas

## Resultado

## Supuestos

## Pendientes
```

## Definición de terminado de la Semana 1

La semana se considera terminada cuando la aplicación conserva la navegación
actual, la arquitectura está documentada, las fundaciones de seguridad y
catálogo tienen contratos y pruebas, las vistas están alineadas con esos
contratos, no hay secretos en el repositorio y el Product Owner aprobó los PR
correspondientes.
