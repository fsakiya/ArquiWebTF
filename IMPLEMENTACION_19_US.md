# 🌱 EcoMinds - Proyecto Completo con 19 User Stories

## 📋 **RESUMEN DEL PROYECTO**

Este documento contiene la **implementación completa** de las 19 User Stories para la aplicación EcoMinds con seguridad JWT.

---

## 🔐 **CONFIGURACIÓN INICIAL**

### 1. Base de Datos PostgreSQL
```sql
CREATE DATABASE db_EcoMind;
```

### 2. Configuración (`application.properties`)
Ya actualizado con:
- Conexión a PostgreSQL
- JWT secret y expiration
- Hibernate configurado

---

## 📦 **ENTIDADES CREADAS/ACTUALIZADAS**

### ✅ Entidades de Seguridad:
1. **Users** - Usuario para autenticación (creada)
2. **Role** - Roles del sistema (creada)

### ✅ Entidades del Negocio (ya existen):
3. **Usuario** - Datos del usuario final
4. **Reciclaje** - Registro de recolecciones (US-002)
5. **MaterialDeReciclaje** - Tipos de materiales reciclables
6. **PuntoDeAcopio** - Puntos de recolección (US-003, US-015, US-016, US-017)
7. **Evento** - Eventos ambientales (US-004, US-008, US-009, US-010, US-011)
8. **Organizador** - Organizadores de eventos
9. **Recompensa** - Catálogo de recompensas (US-005, US-006, US-012, US-013, US-014, US-018, US-019)
10. **Canje** - Registro de canjes (US-006)

---

## 🎯 **MAPEO DE USER STORIES A ENDPOINTS**

### 🔐 **Autenticación y Registro**

#### US-001: Crear una cuenta
**Endpoint:** `POST /api/auth/register`
```json
{
  "username": "juan123",
  "email": "juan@example.com",
  "password": "password123",
  "nombre": "Juan Pérez"
}
```

#### US-007: Iniciar sesión
**Endpoint:** `POST /api/auth/login`
```json
{
  "username": "juan123",
  "password": "password123"
}
```
**Respuesta:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "juan123",
  "roles": ["USER"]
}
```

---

### ♻️ **Reciclaje**

#### US-002: Registrar recolección de residuos
**Endpoint:** `POST /api/reciclajes`
**Headers:** `Authorization: Bearer {token}`
```json
{
  "idUsuario": 1,
  "idMaterial": 1,
  "idPuntoAcopio": 1,
  "fecha": "2025-10-03",
  "hora": "14:30:00",
  "peso": 5.5
}
```

---

### 📍 **Puntos de Acopio**

#### US-003: Consultar puntos de acopio
**Endpoint:** `GET /api/puntos-acopio`
**Headers:** `Authorization: Bearer {token}`

#### US-015: Crear Punto de Acopio (ADMIN)
**Endpoint:** `POST /api/puntos-acopio`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ADMIN
```json
{
  "nombre": "Punto Lima Centro",
  "ubicacion": "Av. Arequipa 1234, Lima"
}
```

#### US-016: Editar Punto de Acopio (ADMIN)
**Endpoint:** `PUT /api/puntos-acopio/{id}`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ADMIN

#### US-017: Eliminar Punto de Acopio (ADMIN)
**Endpoint:** `DELETE /api/puntos-acopio/{id}`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ADMIN

---

### 🎉 **Eventos Ambientales**

#### US-004: Consultar eventos ambientales (ordenados por fecha próxima)
**Endpoint:** `GET /api/eventos/proximos`
**Headers:** `Authorization: Bearer {token}`

#### US-008: Inscribirse en un evento
**Endpoint:** `POST /api/eventos/{id}/inscribirse`
**Headers:** `Authorization: Bearer {token}`

#### US-009: Crear evento (ORGANIZADOR)
**Endpoint:** `POST /api/eventos`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ORGANIZADOR
```json
{
  "nombreEvento": "Limpieza de Playas",
  "descripcion": "Jornada de limpieza en Costa Verde",
  "fechaHora": "2025-10-15T09:00:00",
  "lugar": "Costa Verde, Miraflores",
  "tipoActividad": "Limpieza",
  "recomendaciones": "Traer protector solar y agua",
  "idOrganizador": 1
}
```

#### US-010: Editar descripción de evento (ORGANIZADOR)
**Endpoint:** `PUT /api/eventos/{id}`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ORGANIZADOR

#### US-011: Eliminar evento (ORGANIZADOR)
**Endpoint:** `DELETE /api/eventos/{id}`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ORGANIZADOR

---

### 🎁 **Recompensas**

#### US-005: Visualizar catálogo de recompensas
**Endpoint:** `GET /api/recompensas`
**Headers:** `Authorization: Bearer {token}`

#### US-006: Canjear recompensas con puntos
**Endpoint:** `POST /api/canjes`
**Headers:** `Authorization: Bearer {token}`
```json
{
  "idUsuario": 1,
  "idRecompensa": 2,
  "puntosUtilizados": 100
}
```

#### US-012: Crear recompensa (ADMIN)
**Endpoint:** `POST /api/recompensas`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ADMIN
```json
{
  "nombreRecompensa": "Botella Térmica Ecológica",
  "descripcion": "Botella de acero inoxidable 750ml",
  "puntosRequeridos": 150,
  "categoria": "Productos Sostenibles",
  "stock": 50,
  "disponible": true
}
```

#### US-013: Editar recompensa (ADMIN)
**Endpoint:** `PUT /api/recompensas/{id}`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ADMIN

#### US-014: Eliminar recompensa (ADMIN)
**Endpoint:** `DELETE /api/recompensas/{id}`
**Headers:** `Authorization: Bearer {token}`
**Rol requerido:** ADMIN

#### US-018: Ordenar recompensas de menor a mayor costo
**Endpoint:** `GET /api/recompensas/ordenar/asc`
**Headers:** `Authorization: Bearer {token}`

#### US-019: Ordenar recompensas de mayor a menor costo
**Endpoint:** `GET /api/recompensas/ordenar/desc`
**Headers:** `Authorization: Bearer {token}`

---

## 🔑 **ROLES DEL SISTEMA**

1. **USER** - Usuario estándar (puede reciclar, ver recompensas, canjear, inscribirse a eventos)
2. **ADMIN** - Administrador (gestión completa de recompensas y puntos de acopio)
3. **ORGANIZADOR** - Organizador de eventos (gestión de eventos)

---

## 📁 **ARCHIVOS QUE FALTAN POR CREAR**

### 🔐 Seguridad (en `securities/`):
1. ✅ `JwtUtil.java` - Ya creado
2. ⏳ `JwtRequestFilter.java` - Filtro de autenticación
3. ⏳ `WebSecurityConfig.java` - Configuración de seguridad
4. ⏳ `JwtAuthenticationEntryPoint.java` - Manejo de errores 401
5. ⏳ `UserDetailsServiceImpl.java` - Servicio de detalles de usuario

### 📦 Repositorios (en `repositorios/`):
- ⏳ `UserRepository.java`
- ⏳ `RoleRepository.java`
- Los demás ya existen o necesitan actualizarse

### 🎯 Servicios (en `servicios/` e `intefaces/`):
- Crear servicios para cada entidad
- Implementar lógica de negocio

### 🎮 Controladores (en `controller/`):
- ⏳ `AuthController.java` - Login y registro
- Actualizar controladores existentes

### 📊 DTOs (en `dto/`):
- ⏳ `LoginRequest.java`
- ⏳ `LoginResponse.java`
- ⏳ `RegisterRequest.java`
- Los demás DTOs ya existen o necesitan actualizarse

---

## 🚀 **PRÓXIMOS PASOS**

1. ✅ Dependencias JWT agregadas al `pom.xml`
2. ✅ `application.properties` configurado
3. ✅ `JwtUtil.java` creado
4. ⏳ Crear archivos de seguridad restantes
5. ⏳ Crear repositorios
6. ⏳ Crear servicios
7. ⏳ Crear controladores
8. ⏳ Crear DTOs
9. ⏳ Crear script `data.sql` con datos iniciales
10. ⏳ Probar todos los endpoints

---

## 🧪 **USUARIOS DE PRUEBA (data.sql)**

```sql
-- Contraseña encriptada con BCrypt: admin123, user123, org123
-- Contraseña encriptada con BCrypt: "password123" --- IGNORE ---
INSERT INTO users (username, email, password, nombre, enabled, puntos_acumulados) VALUES
('admin', 'admin@ecomind.com', '$2a$12$s6UnHgsBaQv5YwboB7HSye8d0ZaS7WsGd9Nm4CFYR8C8j5jBRcrmi', 'Administrador', true, 0),
('user1', 'user1@example.com', '$2a$12$Jgcy4hIgw/RIBB/rBf7bWOsslt6Ya9dLcxMNuQ77v3EYce/FCbP8S', 'Juan Pérez', true, 150),
('organizador1', 'org1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 'María López', true, 0);

INSERT INTO roles (rol, user_id) VALUES
('ADMIN', 1),
('USER', 2),
('ORGANIZADOR', 3);
```

---

## 📝 **NOTAS IMPORTANTES**

- JWT expira en 24 horas (86400000 ms)
- Las contraseñas se encriptan con BCrypt
- Todos los endpoints (excepto login/register) requieren autenticación
- Los roles se validan con `@PreAuthorize` en los controladores

---

**¿Quieres que continúe creando los archivos restantes?** 🚀
