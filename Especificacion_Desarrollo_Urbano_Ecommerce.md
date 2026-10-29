# Especificación funcional y técnica para desarrollar Urbano Ecommerce

**Proyecto:** Tiendas Urbano  
**Propósito:** Documento de traspaso para una IA de desarrollo y varios agentes colaboradores.  
**Uso previsto:** Implementación de una demostración académica funcional, privada y ejecutada localmente.  
**Versión:** 1.0 — 29 de octubre de 2026.

## 1. Instrucción principal para la IA de desarrollo

Lee este documento completo antes de modificar el repositorio. Implementa el MVP de Urbano Ecommerce respetando el contexto del negocio, las funcionalidades, los controles de seguridad, las historias de usuario y sus criterios de aceptación. No agregues funcionalidades ajenas al alcance, no reemplaces tecnologías sin justificarlo y no dejes pantallas que parezcan terminadas pero no realicen las operaciones descritas.

Antes de crear código, inspecciona los archivos y la estructura existente. Si el repositorio ya tiene implementación, conserva lo válido y propone cambios incrementales. No sobrescribas ni elimines trabajo sin comprobar dependencias. Mantén una única arquitectura y un contrato de datos compartido entre los agentes. Si falta una decisión imprescindible, documenta el supuesto más sencillo y seguro para la demostración, sin bloquear todo el trabajo.

El proyecto debe ejecutarse localmente, sin publicar el sitio en Internet ni contratar servicios pagados. Cualquier integración que requiera claves, certificaciones o cuentas externas debe ofrecer un modo de demostración y quedar aislada detrás de una interfaz reemplazable.

## 2. Contexto del negocio

Tiendas Urbano es una empresa importadora y vendedora de ropa femenina, con aproximadamente diez años de operación. Importa prendas desde China y comercializa productos como poleras, blazers, pantalones, parkas, chaquetas y trajes de baño. No dispone de tienda física; sus ventas se han realizado principalmente por Internet y redes sociales. La bodega central gestiona las existencias, prepara solicitudes y coordina despachos mediante empresas de transporte para Santiago y otras regiones.

Durante los últimos dos años se han reducido los ingresos y el negocio ha tenido dificultades para mantener márgenes. Entre los problemas del caso se encuentran la dependencia de las redes sociales como canal directo de venta, la desconfianza de algunas clientas ante posibles fraudes, la variedad y control del stock, el registro manual de pedidos y la dificultad de responder a aumentos de demanda.

La compra actual requiere confirmar existencias, registrar el pedido, recibir el pago anticipado del 100 % más el despacho y coordinar la preparación con bodega. La solución debe centralizar el proceso para disminuir tareas manuales y mejorar la trazabilidad.

## 3. Objetivo y visión del producto

**Objetivo:** construir una plataforma web de comercio electrónico y administración que permita a las clientas consultar productos y ofertas, comprar ropa femenina, pagar mediante un flujo de demostración seguro y realizar seguimiento de sus pedidos. Para la empresa, debe centralizar productos, stock, pedidos, despachos, ventas, reportes, contenido y permisos de usuarios.

**Visión:** Urbano Ecommerce será el canal propio de compra y gestión de Tiendas Urbano. Las redes sociales se utilizarán como medios de difusión y dirigirán a las clientas al sitio, donde se gestionará la compra y quedará registro de la operación.

## 4. Alcance del MVP

### Incluye

- Sitio público adaptable a computadores y móviles.
- Catálogo de ropa femenina, ficha de producto, búsqueda, categorías, tallas, colores, precio y stock disponible.
- Ofertas y contenido comercial administrables.
- Registro, confirmación de correo en modo local y acceso de clientas.
- Carrito, checkout, dirección de despacho y resumen de compra.
- Pago de demostración que simule aprobación o rechazo, sin cobrar dinero real ni almacenar datos de tarjetas.
- Registro de pedido, boleta/comprobante de demostración imprimible y consulta del estado.
- Administración de productos, precios, ofertas y stock.
- Listado de pedidos y de despachos diarios.
- Resúmenes de compra/venta e informes básicos con filtros por período.
- Notificaciones de demostración y configuración opcional de correo SMTP.
- Roles y permisos para clienta, administrador, bodega/despacho y gestión de contenido.
- Registro de auditoría de acciones administrativas relevantes.
- Datos de ejemplo ficticios, pruebas y guía de ejecución local.

### Fuera del alcance inicial

- Publicación del sitio en Internet o despliegue productivo.
- Tienda física, aplicación móvil nativa o servicio de reparto propio.
- Negociación con proveedores de China, gestión de transporte marítimo o logística de importación.
- Procesamiento real de tarjetas o cargos monetarios. La pasarela real solo se integrará si el equipo entrega credenciales válidas y solicita expresamente esa ampliación.
- Integración tributaria real con el SII. El documento generado se identificará como **boleta/comprobante de demostración no válido como documento tributario**.
- Integraciones pagadas, servicios de correo pagados o APIs externas que necesiten credenciales no disponibles.
- Recomendaciones personalizadas basadas en inteligencia artificial en el MVP. El historial de compras podrá almacenarse con permisos adecuados para una eventual ampliación.

## 5. Usuarios y permisos

### Clienta

Puede navegar el catálogo y las ofertas, buscar productos, registrarse, iniciar y recuperar acceso, utilizar el carrito, confirmar pedidos, completar el pago simulado y consultar solamente sus propios pedidos y comprobantes.

### Administrador / dueño

Puede mantener productos, categorías, precios, stock, ofertas, pedidos, reportes y resúmenes de ventas/compras; administrar roles de usuarios y revisar la auditoría. Sus permisos no deben estar disponibles para cuentas de clientas.

### Encargado de bodega / despacho

Puede consultar pedidos confirmados y pagados, revisar las direcciones y los productos, preparar los envíos y actualizar los estados de preparación y despacho dentro de los permisos asignados. No debe acceder a contraseñas ni a información financiera que no necesita.

### Encargado de contenido / publicidad

Puede modificar contenido comercial, ofertas e hipervínculos a redes sociales. No puede cambiar roles, consultar credenciales ni administrar pagos.

### Regla obligatoria de seguridad de roles

El registro público siempre crea una cuenta con rol `CLIENTE`. Nunca se debe permitir que una persona seleccione `ADMIN`, `BODEGA` o `CONTENIDO` desde el formulario de registro. La asignación de roles privilegiados solo puede realizarla un administrador autenticado y autorizado.

## 6. Funciones y pantallas que deben construirse

### Sitio público

1. **Inicio:** identidad visual de la tienda, acceso a categorías, ofertas destacadas, productos destacados y enlaces a redes sociales.
2. **Catálogo:** listado paginado de productos activos y con información actualizada, filtros básicos por categoría, talla, color y disponibilidad.
3. **Detalle del producto:** nombre, descripción, imágenes, precio, oferta vigente si aplica, tallas, colores y stock disponible.
4. **Ofertas:** sección de promociones vigentes con precio/condiciones claras.
5. **Búsqueda:** campo de búsqueda y resultados con opción de imprimir o descargar cuando corresponda.
6. **Registro, inicio de sesión y recuperación:** formularios con validación y mensajes comprensibles.
7. **Carrito:** productos, cantidades, precios, subtotal, despacho y total estimado; permitir retirar artículos y corregir cantidades.
8. **Checkout:** solicitar los datos necesarios para el despacho, mostrar el monto total y explicar que el pago es simulado.
9. **Resultado del pago:** confirmar aprobación o rechazo de la simulación, sin mostrar datos internos.
10. **Confirmación de pedido:** número del pedido, resumen y acceso al comprobante de demostración.
11. **Mi cuenta:** información básica de la cuenta, historial de pedidos y seguimiento.
12. **Mis pedidos:** estado, costo, fecha estimada y detalle de cada pedido perteneciente a la clienta autenticada.
13. **Comprobante/boleta demo:** vista imprimible, claramente marcada como demostración no tributaria.

### Panel de administración

1. **Inicio de administración:** indicadores simples de pedidos, ventas, productos con bajo stock y últimos movimientos.
2. **Productos:** listar, crear, editar, activar/desactivar y consultar productos. Evitar eliminar físicamente un producto referenciado en pedidos históricos.
3. **Stock:** registrar entradas y salidas, cantidad, motivo, fecha y usuario que realizó el movimiento. Impedir cantidades negativas.
4. **Ofertas y contenido:** crear/editar/desactivar ofertas, mantener fechas vigentes, contenidos principales y enlaces a redes sociales.
5. **Pedidos:** listar y filtrar pedidos; consultar detalle y cambiar el estado según transiciones autorizadas.
6. **Despachos:** generar el listado por fecha, con número de pedido, dirección y productos; permitir imprimirlo.
7. **Compras y ventas:** presentar resúmenes diarios o por un período seleccionado. Si las compras a proveedores no se desarrollan completamente en el MVP, mostrar al menos el registro disponible de entradas/compra de mercadería e indicar el alcance del informe.
8. **Reportes:** ventas, compras/entradas, stock y despachos; aplicar filtro de fechas y exportar datos permitidos a CSV.
9. **Usuarios y roles:** activar/desactivar cuentas y asignar roles a través de usuarios con privilegios.
10. **Auditoría:** consultar acciones administrativas críticas sin exponer secretos ni datos personales innecesarios.

## 7. Flujo principal de compra

1. La clienta navega el catálogo y revisa el producto, su talla/color y stock.
2. Agrega artículos al carrito y revisa cantidades, precios y subtotal.
3. Inicia sesión o se registra; el sistema valida la información requerida.
4. Ingresa o confirma los datos de despacho. El sistema calcula el total a pagar sumando subtotal y costo de despacho.
5. Antes del pago, vuelve a verificar que el stock siga disponible y que los precios sean vigentes.
6. La clienta ejecuta el pago simulado por el 100 % del total, incluido el despacho.
7. Si el pago es aprobado, se registra la operación, se descuenta o reserva el stock de forma atómica, se crea el pedido pagado, se genera el comprobante de demostración y se prepara la notificación.
8. Si el pago es rechazado, no se marca el pedido como pagado, no se genera un comprobante de pago aprobado y no se descuenta stock como venta confirmada.
9. Bodega prepara el pedido; luego el usuario autorizado registra el despacho y la información de entrega.
10. La clienta consulta el progreso de su pedido desde su cuenta.

El costo de despacho debe ser configurable para la demostración o ingresado por un usuario autorizado. No inventar una tabla de tarifas real por comuna o región, porque el caso no proporciona esos datos.

## 8. Estados y reglas de pedidos

Usar un conjunto consistente de estados, por ejemplo:

- `PENDIENTE_PAGO`
- `PAGO_RECHAZADO`
- `PAGADO`
- `EN_PREPARACION`
- `DESPACHADO`
- `ENTREGADO`
- `CANCELADO`

El agente puede ajustar los nombres para la implementación, pero debe documentarlos y usarlos uniformemente. Solo usuarios autorizados pueden cambiar los estados operativos. No debe permitirse despachar un pedido impago. Las transiciones que modifiquen stock, pago o estado deben ejecutarse dentro de transacciones de base de datos para evitar inconsistencias.

## 9. Requisitos funcionales refinados

### RF-01 — Registro de clientas
Permitir registro con nombre, RUT, correo, teléfono y contraseña. Validar campos, unicidad del correo y formato del RUT cuando se implemente su validador. La cuenta requiere confirmación del correo; en modo local se puede usar un enlace/token de demostración o registrar el mensaje en una consola/caja de salida local.

### RF-02 — Autenticación y recuperación
Permitir iniciar sesión, cerrar sesión y solicitar recuperación de contraseña mediante el correo registrado. Evitar mensajes que revelen innecesariamente si una cuenta existe.

### RF-03 — Catálogo y búsqueda
Mostrar productos activos, precios, descripción, categoría, imágenes, tallas, colores y disponibilidad. Permitir búsqueda por texto y filtros relevantes. No publicar información interna como costo de importación, margen o anotaciones de bodega.

### RF-04 — Ofertas y contenido comercial
Permitir consultar las ofertas vigentes y al personal autorizado administrarlas, manteniendo contenido institucional y enlaces a redes sociales. La compra se concreta en Urbano Ecommerce, no por redes sociales.

### RF-05 — Carrito de compra
Permitir agregar, retirar y cambiar cantidades de productos; recalcular subtotal y total y verificar disponibilidad antes de continuar.

### RF-06 — Registro de pedidos
Registrar cada pedido con número único, clienta, detalle de artículos, cantidades, precios al momento de la compra, subtotal, despacho, total, dirección de entrega y estado. Mantener una fotografía histórica de los datos comerciales del pedido aunque el producto sea editado después.

### RF-07 — Pago online de demostración
Incluir un adaptador de pago que permita simular aprobación y rechazo. La lógica de pedido no debe depender de la implementación simulada; se debe poder reemplazar el adaptador por una pasarela real en una etapa futura. No solicitar ni almacenar números reales de tarjeta o CVV.

### RF-08 — Boleta/comprobante de demostración
Generar un comprobante asociado al pedido una vez confirmado el pago simulado. Debe poder visualizarse e imprimirse y debe incluir una advertencia visible de que no es un documento tributario real.

### RF-09 — Seguimiento de pedidos
Permitir a la clienta consultar únicamente sus propios pedidos y ver estado, costo, fecha estimada de entrega y datos de seguimiento disponibles.

### RF-10 — Gestión de productos y stock
Permitir a usuarios autorizados crear y editar productos, activar/desactivar publicaciones y registrar movimientos de stock. Impedir inventario negativo y mantener trazabilidad de entradas y salidas.

### RF-11 — Resúmenes de compras y ventas
Permitir al dueño/administrador consultar resultados diarios y por período. Restringir la información financiera a los roles autorizados. Los datos de compras de mercadería se deben limitar a lo que el MVP efectivamente registre.

### RF-12 — Listado de despachos
Generar una lista por fecha de pedidos listos para despacho, mostrando productos, cantidades, dirección y número de pedido. Debe poder imprimirse para apoyar la operación de bodega.

### RF-13 — Reportes de gestión
Generar informes de ventas, compras/entradas, stock y despachos con filtros de período y exportación CSV cuando aplique. Las exportaciones deben respetar los permisos y minimizar datos personales.

### RF-14 — Notificaciones por correo
Preparar confirmaciones de registro, pedido, pago y cambios de estado. En modo local, permitir una salida de demostración visible en logs o una bandeja local de correos. Si hay SMTP configurado mediante variables de entorno, podrá enviarse correo real.

### RF-15 — Roles, permisos y auditoría
Controlar el acceso por roles. Registrar usuario, fecha/hora, acción y entidad afectada para operaciones relevantes, por ejemplo cambios de rol, stock, producto, pedido y estado de despacho. No registrar contraseñas, tokens, secretos ni datos completos de pago.

## 10. Requisitos no funcionales

### RNF-01 — Rendimiento visual
Los contenidos visuales principales deberán estar disponibles en un máximo de 15 segundos en las condiciones normales de prueba definidas para el proyecto.

### RNF-02 — Capacidad de respuesta
Como meta de prueba académica propuesta —no un dato original del caso— se propone probar 50 usuarios concurrentes y comprobar que al menos el 95 % de las respuestas de las operaciones de lectura definidas se complete en 3 segundos o menos. Si el entorno local no permite una prueba representativa, documentar la limitación y el resultado real.

### RNF-03 — Credenciales
Almacenar contraseñas usando un algoritmo de hash de contraseñas adecuado, como BCrypt. No guardar contraseñas en texto plano ni en logs.

### RNF-04 — Autorización
Aplicar autorización en el servidor y el mínimo privilegio. Ocultar enlaces en la interfaz no sustituye la validación de permisos del backend.

### RNF-05 — Seguridad de pagos
No almacenar datos sensibles de tarjetas. En modo de demostración, usar solo datos ficticios y resultados simulados.

### RNF-06 — Protección e integridad de datos
Proteger datos personales de clientas y datos empresariales como costos, stock, ventas y reportes. Validar las entradas y mantener consistencia entre pedidos, pagos y movimientos de stock.

### RNF-07 — Auditoría y recuperación
Registrar las acciones administrativas críticas y documentar cómo respaldar y restaurar la base local. Los registros no deben contener contraseñas, tokens ni datos sensibles innecesarios.

### RNF-08 — Privacidad
Solicitar solo datos necesarios para registro, compra y despacho; restringir las consultas a la información que cada usuario está autorizado a ver; no utilizar datos personales reales para las demostraciones.

### RNF-09 — Accesibilidad
Considerar las pautas WCAG 2.0 y las referencias de SENADIS presentes en el caso: etiquetas en formularios, navegación por teclado, contraste suficiente, texto alternativo y mensajes de validación claros.

### RNF-10 — Usabilidad
Mantener navegación clara e intuitiva, formularios consistentes, mensajes de error comprensibles y confirmaciones antes de acciones administrativas importantes.

### RNF-11 — Compatibilidad y estándares
Usar HTML semántico y buenas prácticas compatibles con W3C; comprobar el funcionamiento en los navegadores principales disponibles en el equipo.

### RNF-12 — Diseño adaptable y acceso web
La interfaz debe adaptarse a escritorio y móvil, y funcionar desde navegador sin requerir instalación de una aplicación nativa.

### RNF-13 — Mantenibilidad e integración
Separar controladores, lógica de negocio, acceso a datos y presentación. Las integraciones de pago y correo deben usar interfaces/adaptadores reemplazables. Las claves se obtienen desde variables de entorno y nunca se incluyen en Git.

## 11. Reglas de ciberseguridad y datos

- Todas las rutas privadas deben requerir autenticación y autorización del servidor.
- Aplicar protección CSRF en formularios con sesión; no desactivarla globalmente para simplificar.
- Validar los datos tanto en el navegador como en el backend. La validación frontend mejora la experiencia, pero no sustituye la validación de servidor.
- Usar consultas parametrizadas/ORM; nunca construir SQL concatenando entradas de usuario.
- Escapar adecuadamente contenido renderizado para reducir riesgo de XSS.
- Evitar exponer stack traces, consultas SQL, tokens o detalles internos en errores de usuario.
- Restringir los endpoints de administración, reportes, stock, usuarios, auditoría y exportación a los roles autorizados.
- Guardar contraseña como hash; para recuperación, usar tokens temporales de un solo uso y no enviar la contraseña por correo.
- No almacenar datos de tarjetas, CVV, claves de correo, contraseñas de base de datos ni secretos de API en el repositorio.
- Mantener el registro de auditoría con los datos mínimos necesarios y evitar copiar datos personales completos en los detalles de auditoría.
- Desactivar en lugar de borrar productos que aparezcan en pedidos históricos.
- Registrar movimientos de stock con actor, fecha, cantidad y motivo.
- Usar datos ficticios para las cuentas, RUT, direcciones, pedidos y pagos de demostración.
- Proteger los archivos exportados y limitar sus campos a los necesarios para el propósito del informe.
- Mantener variables sensibles en archivo local ignorado por Git o variables de entorno.

## 12. Modelo de datos propuesto

El agente de arquitectura debe ajustar los nombres y relaciones al modelo real que implemente, sin duplicar datos innecesariamente. Se sugieren estas entidades:

- **User:** id, nombre, RUT, email único, teléfono, passwordHash, emailVerified, activo, rol, fechas de creación/actualización.
- **Role:** enum o catálogo de roles con permisos inequívocos.
- **Product:** id, SKU único, nombre, descripción, categoría, precio público, talla(s), color(es), imagen, stock, estado activo, fechas.
- **Offer:** producto o conjunto de productos, precio/descuento, vigencia, estado activo, usuario creador/actualizador.
- **Cart / CartItem:** usuario o sesión, producto, cantidad; los precios finales se vuelven a calcular al confirmar pedido.
- **Order:** id, número único, usuario, estado, subtotal, costo de despacho, total, dirección de entrega como fotografía histórica, fecha, fecha estimada.
- **OrderItem:** pedido, producto opcional de referencia, SKU/nombre/precio unitario históricos, talla/color y cantidad.
- **Payment:** pedido, proveedor/adaptador, monto, estado, referencia de demostración y fecha. Nunca guardar información de tarjeta.
- **Receipt:** pedido, folio de demostración, fecha y referencia al comprobante imprimible.
- **StockMovement:** producto, tipo entrada/salida/ajuste, cantidad, motivo, actor y fecha.
- **Shipment:** pedido, transportista si se registra, código de seguimiento opcional, estado y fechas.
- **NotificationLog:** destinatario, tipo, resultado de envío y fecha, sin incluir secretos.
- **AuditEvent:** actor, acción, entidad afectada, id, fecha/hora y detalle mínimo seguro.

Respetar las claves foráneas y restricciones de unicidad. Usar transacciones para confirmar pago, cambiar stock y actualizar estado relacionado. Evitar eliminar datos históricos utilizados por pedidos ya creados.

## 13. Stack técnico recomendado

La preferencia del proyecto es Java, programación orientada a objetos, Maven, IntelliJ IDEA y base de datos Oracle administrable mediante SQL Developer. Para evitar complejidad innecesaria en un MVP local, se recomienda:

- **Backend:** Java 21 LTS si está disponible en el entorno del equipo, Spring Boot, Spring MVC.
- **Construcción:** Maven con versiones de dependencias compatibles, declaradas en `pom.xml`.
- **Frontend web:** Thymeleaf para generar las páginas desde el servidor, más HTML semántico, CSS y JavaScript mínimo para interacciones de interfaz.
- **Persistencia:** Spring Data JPA / Hibernate y Oracle como base principal, con configuración externa. SQL Developer se usa para inspeccionar y administrar la base, no como motor de aplicación.
- **Seguridad:** Spring Security, contraseñas con BCrypt y autorización por rol.
- **Validación:** Jakarta Bean Validation en DTO/formularios y validación adicional en servicios cuando corresponda.
- **Pruebas:** JUnit y pruebas de servicios/controladores; las pruebas de integración deben usar una base compatible con el entorno de desarrollo.

**Nota sobre frontend y Java:** los navegadores no ejecutan Java directamente como lenguaje de interfaz moderno. Con Thymeleaf, Java procesa las plantillas en el servidor y el navegador recibe HTML/CSS/JavaScript. Esto mantiene una solución Java de extremo a extremo en la aplicación sin introducir un frontend separado con Node/React, lo que reduce trabajo de configuración. Si el equipo exige que la lógica de interfaz también sea Java, se debe evaluar Vaadin como alternativa y no mezclar dos enfoques en el mismo MVP.

### Organización de carpetas sugerida

```text
urbano-ecommerce/
├── pom.xml, README.md, .gitignore, .env.example
├── docs/ (ESPECIFICACION_DESARROLLO.md, ARQUITECTURA.md, DECISIONES_TECNICAS.md)
├── src/main/java/.../urbano/{config,security,controller,dto,entity,repository,service,validation,audit,payment,notification,exception}/
├── src/main/resources/ (application.yml, db/migration, templates/{fragments,public,auth,account,admin,warehouse}, static/{css,js,images})
└── src/test/java/.../urbano/
```

No crear paquetes duplicados ni otro proyecto dentro del repositorio sin justificarlo. La ruta de paquete Java se acordará al inicio y todos los agentes la reutilizarán.

## 14. Configuración local

- La aplicación debe iniciarse con `mvn spring-boot:run` después de configurar la base de datos y las variables necesarias.
- El README debe describir versiones requeridas, cómo crear/configurar el esquema Oracle, configurar variables locales, iniciar la aplicación y ejecutar pruebas.
- Incluir `.env.example` con nombres de variables y valores de ejemplo no secretos. Agregar el `.env` real a `.gitignore`.
- Variables recomendadas: `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, `URBANO_ADMIN_EMAIL`, `URBANO_ADMIN_INITIAL_PASSWORD`, `MAIL_HOST`, `MAIL_PORT`, `MAIL_USERNAME`, `MAIL_PASSWORD` y `MAIL_FROM`.
- No imprimir contraseñas al arrancar. La cuenta inicial de administración debe crearse de manera controlada, con contraseña inicial configurada por entorno y hash almacenado.
- Si Oracle no está disponible para pruebas automáticas, se puede usar un perfil de test con una base embebida compatible, sin cambiar el objetivo de persistencia Oracle del perfil local principal.
- La aplicación se ejecuta en `localhost`; no abrir puertos externos ni configurar despliegue público.

## 15. Criterios de aceptación por historia de usuario

### HU-01 — Registro de clientas (RF-01)
- El formulario solicita nombre, RUT, correo, teléfono y contraseña.
- Los campos obligatorios se validan en frontend y backend.
- El correo no puede repetirse y el RUT debe cumplir el formato definido.
- La cuenta se crea con rol `CLIENTE`; el usuario no puede seleccionar privilegios.
- La contraseña se almacena como hash y nunca se muestra en logs.
- La cuenta queda pendiente de confirmación hasta completar el mecanismo definido para modo local.

### HU-02 — Acceso y recuperación (RF-02)
- Una cuenta confirmada puede iniciar y cerrar sesión.
- Una contraseña incorrecta no permite acceso y muestra un mensaje seguro.
- La recuperación usa un token temporal; no envía la contraseña actual.
- Las rutas privadas no se pueden abrir directamente sin permisos.

### HU-03 — Catálogo y búsqueda (RF-03)
- Se muestran solo productos activos y publicables.
- Las fichas presentan nombre, descripción, precio, categoría, talla/color y stock disponible.
- La búsqueda y los filtros entregan resultados consistentes.
- No se exponen costos internos, margen ni notas privadas.
- La interfaz se puede usar en escritorio y móvil.

### HU-04 — Ofertas y contenido comercial (RF-04)
- Las clientas ven ofertas vigentes y condiciones claras.
- Solo el rol de contenido o administrador puede crear/editar/desactivar ofertas y contenido.
- Las ofertas tienen precio y vigencia válidos; una oferta vencida no se presenta como activa.
- Los enlaces a redes sociales son visibles, pero el proceso de compra se completa en el sitio.

### HU-05 — Carrito (RF-05)
- Se pueden agregar, retirar y cambiar cantidades.
- No se permite agregar un producto inactivo ni cantidades mayores al stock.
- Cantidades, subtotales y total se recalculan correctamente.
- El precio y la disponibilidad se revalidan en checkout.

### HU-06 — Pedidos (RF-06)
- La confirmación crea un pedido con número único y sus datos históricos.
- Se guardan artículos, cantidades, precios unitarios, subtotal, despacho y total.
- Se guarda la dirección necesaria para la entrega sin permitir que otras clientas la consulten.
- El pedido queda en un estado inicial consistente con el pago pendiente.

### HU-07 — Pago simulado (RF-07)
- El flujo ofrece una respuesta simulada aprobada o rechazada.
- El monto a pagar coincide con el subtotal más el despacho.
- Solo una respuesta aprobada cambia el pago a confirmado y permite continuar al estado de preparación.
- Un rechazo no marca el pedido como pagado ni descuenta stock como venta confirmada.
- No se solicitan ni guardan números reales de tarjeta o CVV.

### HU-08 — Boleta/comprobante demo (RF-08)
- Se emite solo después de confirmar el pago simulado.
- El comprobante queda asociado al pedido y presenta folio, fecha, artículos y total.
- Se puede imprimir desde navegador.
- Indica claramente que es demostración y no un documento tributario real.

### HU-09 — Seguimiento (RF-09)
- La clienta ve sus propios pedidos y no los de otras cuentas.
- El detalle muestra estado, total/costo y fecha estimada cuando esté disponible.
- Los cambios de estado aparecen al volver a consultar el pedido.
- Un intento de consulta no autorizada devuelve una respuesta segura.

### HU-10 — Productos y stock (RF-10)
- El usuario autorizado puede crear, editar, activar y desactivar productos.
- Las entradas/salidas de stock registran cantidad, motivo, actor y fecha.
- Se impide stock negativo y se valida el movimiento contra la existencia actual.
- No se elimina físicamente un producto con referencias históricas necesarias.

### HU-11 — Resúmenes de compras y ventas (RF-11)
- El administrador/dueño consulta resúmenes diarios y por período.
- Los totales se calculan a partir de operaciones registradas, no de valores de muestra fijos en la pantalla.
- Las cuentas sin permiso no pueden consultar información financiera.

### HU-12 — Despachos (RF-12)
- Se puede generar el listado de una fecha con pedidos elegibles para despacho.
- Cada entrada identifica pedido, dirección y artículos/cantidades.
- Los pedidos impagos no aparecen como listos para envío.
- El listado se imprime y solo acceden roles autorizados.

### HU-13 — Reportes (RF-13)
- Los reportes muestran datos de ventas, compras/entradas, stock y despachos.
- Se pueden filtrar por fechas cuando aplique.
- Las exportaciones CSV contienen solo los campos necesarios y respetan permisos.
- Los totales del reporte coinciden con los registros subyacentes.

### HU-14 — Notificaciones (RF-14)
- El sistema prepara mensajes para registro, pedido, pago y cambios de estado.
- En local, el mensaje puede registrarse en un log o bandeja de salida de demostración.
- Si SMTP está configurado, el envío utiliza variables de entorno y no secretos en el código.
- Un fallo de notificación no altera la consistencia del pedido o del pago.

### HU-15 — Roles y auditoría (RF-15)
- Los usuarios tienen un rol y los endpoints verifican permisos en el servidor.
- Solo un administrador autorizado puede asignar roles privilegiados.
- Se registran cambios relevantes de usuarios, roles, productos, stock, pedidos y despachos.
- Cada evento incluye actor, fecha/hora, acción y referencia del recurso afectado.
- La auditoría no contiene contraseñas, tokens, datos de tarjeta ni detalles sensibles innecesarios.

## 16. Definition of Done (DoD)

Una historia se considera terminada solo cuando:

- Cumple todos sus criterios de aceptación.
- La lógica principal está implementada en la capa correcta y no solo simulada visualmente, excepto los proveedores que se declaren expresamente simulados.
- Se validaron los datos en el backend.
- Se verificaron autenticación y permisos para las rutas afectadas.
- Se probaron los casos exitosos y al menos los casos de error principales.
- Se revisó que no haya contraseñas, tokens, credenciales o datos sensibles en el código ni en los logs.
- Las modificaciones de pedidos/pagos/stock mantienen la integridad transaccional.
- La interfaz es usable con teclado y pantalla móvil para los flujos principales.
- El código fue revisado por otro integrante/agente y se ejecutan las pruebas automáticas correspondientes.
- Se actualizó la documentación si cambió la configuración, el modelo de datos o un flujo importante.
- El Product Owner puede demostrar la funcionalidad y validar el resultado.

## 17. Product Backlog, prioridad y estimación

Orden de implementación acordado, por valor y dependencias:

- **Prioridad alta — Sprint 1: Fundaciones, catálogo y ciberseguridad.** HU-01 Registro (40 h), HU-02 Acceso (24 h), HU-03 Catálogo (40 h), HU-15 Roles y auditoría (24 h). Esfuerzo total: 128 horas-equipo.
- **Prioridad alta — Sprint 2: Compra, pago y stock.** HU-05 Carrito (32 h), HU-06 Pedidos (32 h), HU-07 Pago simulado (32 h), HU-10 Productos y stock (32 h). Esfuerzo total: 128 horas-equipo.
- **Prioridad alta — Sprint 3: Operación y seguimiento.** HU-08 Boleta demo (32 h), HU-09 Seguimiento (32 h), HU-12 Despachos (32 h), HU-14 Notificaciones (24 h). Esfuerzo total: 120 horas-equipo.
- **Prioridad media — Sprint 4: Gestión, reportes y comunicación.** HU-04 Ofertas/contenido (24 h), HU-11 Resúmenes (40 h), HU-13 Reportes (32 h). Esfuerzo total: 96 horas-equipo.

Esfuerzo preliminar total: **472 horas-equipo = 59 días-equipo de 8 horas = 11,8 semanas-equipo**. La estimación es académica y no se basa todavía en velocidad histórica medida. Las horas representan esfuerzo total, no duración calendario directa.

**Importante para la implementación de un mes:** los cuatro bloques anteriores provienen del plan académico de cuatro sprints de cuatro semanas (16 semanas). Ese plan no equivale a una entrega real en un mes. Para el MVP de un mes, utilizar la planificación semanal de la sección siguiente, dividir el trabajo entre agentes y reevaluar el alcance según la capacidad real del equipo. No ocultar esta diferencia en la documentación.

## 18. Plan de implementación acelerado en cuatro semanas

### Semana 1 — Fundaciones seguras
- Crear estructura Maven/Spring Boot, configuración Oracle, estilos comunes y navegación.
- Crear entidades iniciales, migraciones y datos ficticios de catálogo.
- Implementar registro, acceso, recuperación en modo local, roles y autorización.
- Construir catálogo, detalle y búsqueda básica.
- Criterio de salida: aplicación inicia localmente, se puede autenticar y el catálogo lee datos de la base.

### Semana 2 — Compra y stock
- Implementar carrito, checkout, dirección y resumen de total.
- Implementar pedido, estados y persistencia histórica de productos/precios.
- Integrar el adaptador de pago simulado.
- Implementar administración de productos, movimientos y control de stock.
- Criterio de salida: una clienta puede crear un pedido de demostración y el stock queda consistente ante aprobación/rechazo.

### Semana 3 — Operación y posventa
- Implementar comprobante no tributario imprimible.
- Implementar historial y seguimiento de pedidos.
- Implementar listados de preparación/despacho y cambios de estado.
- Implementar notificaciones locales y configuración opcional SMTP.
- Criterio de salida: desde el panel se puede preparar y marcar un pedido como despachado, y la clienta puede ver el estado.

### Semana 4 — Gestión y estabilización
- Implementar ofertas/contenido, resúmenes y reportes.
- Completar auditoría, permisos restantes y exportación controlada.
- Ejecutar pruebas de regresión, accesibilidad básica, compatibilidad y casos de seguridad.
- Corregir defectos, revisar el README y validar todos los criterios prioritarios con el Product Owner.
- Criterio de salida: demostración completa de extremo a extremo en entorno local.

## 19. Trabajo con varios agentes de IA

### Reglas de coordinación

1. Crear una rama principal estable y una rama por tarea/agente; no permitir que varios agentes editen simultáneamente los mismos archivos centrales.
2. Antes de codificar, el agente debe revisar esta especificación, el código existente y el contrato de entidades/servicios.
3. Acordar temprano los nombres de paquetes, entidades, DTO, servicios, repositorios, rutas y estados. Documentarlos en `docs/ARQUITECTURA.md`.
4. Un agente puede crear una interfaz/contrato y pruebas, pero debe comunicar los nombres exactos antes de que otros construyan dependencias.
5. Cada agente debe terminar con resumen de archivos cambiados, comandos ejecutados, pruebas superadas, limitaciones conocidas y pasos manuales pendientes.
6. No mezclar el refactor completo con una tarea funcional pequeña. Hacer commits frecuentes y revisables.
7. Integrar y ejecutar `mvn test` después de cada merge. Corregir conflictos en una sola rama integradora.
8. No marcar una HU como terminada solo porque compile o tenga una pantalla. Debe cumplir los criterios de aceptación y DoD.
9. No inventar credenciales, servicios pagados ni datos reales. La pasarela y los correos deben poder funcionar en modo local.
10. No incorporar dependencias nuevas grandes sin explicar su necesidad y compatibilidad con Java/Maven.

### Distribución sugerida de agentes

**Agente coordinador/arquitectura:** inspecciona el repositorio, acuerda estructura, entidades, rutas, estados, contratos de servicio, migraciones y configuración. Mantiene el README y resuelve integración; no debe reescribir módulos terminados sin causa.

**Agente de autenticación y seguridad:** implementa registro, login, recuperación en modo local, Spring Security, roles, autorizaciones, auditoría y pruebas de acceso. Trabaja en los paquetes de seguridad, cuenta y auditoría coordinados con la arquitectura.

**Agente de catálogo y compra:** implementa catálogo, búsqueda, ofertas visibles, carrito, checkout, pedidos y adaptador de pago simulado. Coordina con las entidades compartidas y no crea una segunda versión del modelo de pedidos.

**Agente de administración y operación:** implementa productos/stock, movimientos, despacho, resúmenes, reportes y notificaciones. Respeta los permisos definidos y coordina cualquier cambio en entidades compartidas.

**Agente de interfaz:** puede trabajar en paralelo sobre plantillas Thymeleaf, CSS, JavaScript y diseño responsive si se separan los archivos por pantalla. Debe consumir rutas/DTO ya acordados y nunca implementar seguridad solo ocultando botones.

Si solo se dispone de cuatro agentes, combinar coordinador con una función de desarrollo y mantener una persona/agente responsable de integrar y ejecutar todas las pruebas.

<!-- PAGEBREAK -->

## 20. Prompt maestro para entregar a la IA

Copia el bloque siguiente junto a este documento cuando inicies el desarrollo:

> Actúa como arquitecto y equipo senior de desarrollo Java. Lee toda la especificación de Urbano Ecommerce antes de escribir o modificar código. Tu objetivo es construir un MVP de comercio electrónico y administración para Tiendas Urbano, ejecutable localmente y sin servicios pagos. Inspecciona el repositorio y comunica el plan antes de cambiar archivos. Usa la arquitectura acordada Java/Spring Boot/Maven/Thymeleaf/Oracle, manteniendo HTML/CSS/JavaScript para la interfaz que recibe el navegador. No reemplaces tecnologías ni rehagas código existente sin justificarlo. Implementa funcionalidades reales en capas, validación de backend, autorización por rol, integridad transaccional y pruebas. La pasarela de pago y el correo deben disponer de adaptadores para modo local; no proceses dinero real ni almacenes tarjetas. Usa datos ficticios. No incluyas credenciales en Git. Mantén trazabilidad con RF, HU y criterios de aceptación. Divide el trabajo en tareas pequeñas, define contratos compartidos y evita que agentes distintos sobrescriban los mismos archivos. Antes de dar una HU por terminada, verifica sus criterios de aceptación y Definition of Done. Al finalizar cada tarea, informa archivos cambiados, pruebas ejecutadas, resultados, supuestos y pendientes. Prioriza una solución simple, segura, mantenible y completada antes de agregar características fuera del alcance.

## 21. Lista de verificación de la primera entrega de código

- [ ] El repositorio contiene `README.md` y esta especificación dentro de `docs/`.
- [ ] La aplicación arranca con los comandos documentados.
- [ ] La base de datos se conecta sin secretos guardados en Git.
- [ ] Existen datos ficticios de productos, categorías, tallas, colores y stock.
- [ ] El registro crea cuentas de clientas sin privilegios administrativos.
- [ ] Los roles se verifican en el backend.
- [ ] El catálogo y la búsqueda obtienen datos persistidos.
- [ ] El carrito y el total se recalculan desde información confiable.
- [ ] El pago simulado cubre aprobación y rechazo.
- [ ] Los pedidos y movimientos de stock son consistentes.
- [ ] Las clientas solo pueden consultar sus pedidos.
- [ ] La boleta se identifica como demostración no tributaria.
- [ ] La operación de bodega/despacho está restringida a usuarios autorizados.
- [ ] Reportes, CSV y auditoría respetan permisos y minimización de datos.
- [ ] Las pantallas principales funcionan en escritorio y móvil.
- [ ] Las pruebas relevantes pasan y el README explica límites conocidos.

## 22. Resultado esperado

Al terminar el MVP, Tiendas Urbano tendrá una tienda web local donde una clienta podrá registrarse, consultar ropa femenina, buscar productos, usar el carrito, efectuar un pago simulado, obtener un comprobante demostrativo y revisar el estado de su pedido. El personal autorizado podrá mantener catálogo, ofertas y stock, gestionar pedidos y despachos, consultar reportes y revisar actividades administrativas. La aplicación deberá aplicar controles básicos de seguridad y protección de datos desde su primera versión, con una estructura que permita ampliar posteriormente integraciones reales sin rehacer el sistema completo.
