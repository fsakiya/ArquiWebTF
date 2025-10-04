# 🧪 GUÍA DE PRUEBAS EN POSTMAN - EcoMinds 19 User Stories

## 📋 **CONFIGURACIÓN INICIAL**

### 1. Ejecutar el Proyecto
```bash
cd "ArquiWebTF-master definitivo/ArquiWebTF-master"
mvn clean install
mvn spring-boot:run
```

### 2. Ejecutar Script SQL
Una vez que el servidor esté corriendo y las tablas creadas:
1. Abre **pgAdmin**
2. Conecta a la base de datos `db_EcoMind`
3. Ejecuta el archivo `src/main/resources/datos_iniciales.sql`

---

## 🔐 **PASO 1: AUTENTICACIÓN**

### ✅ US-007: Iniciar Sesión (Login)

**Endpoint:** `POST http://localhost:8080/auth/login`

**Headers:**
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "username": "admin",
  "password": "password123"
}
```

**Respuesta esperada (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "username": "admin",
  "roles": ["ADMIN"]
}
```

**👉 Copia el token para usarlo en los siguientes endpoints**

---

### ✅ US-001: Crear una Cuenta (Registro)

**Endpoint:** `POST http://localhost:8080/auth/register`

**Headers:**
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "username": "nuevouser",
  "email": "nuevo@example.com",
  "password": "password123",
  "nombre": "Usuario Nuevo"
}
```

**Respuesta esperada (201 CREATED):**
```json
"Usuario registrado exitosamente"
```

---

## ♻️ **RECICLAJE**

### ✅ US-002: Registrar Recolección de Residuos

**Endpoint:** `POST http://localhost:8080/api/reciclaje`

**Headers:**
- `Authorization`: `Bearer {tu_token}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "fecha": "2025-10-03",
  "hora": "14:30:00",
  "peso": 5.5,
  "idUsuario": {
    "id": 2
  },
  "idMateriales": {
    "id": 1
  },
  "idAcopio": {
    "id": 1
  }
}
```

---

## 📍 **PUNTOS DE ACOPIO**

### ✅ US-003: Consultar Puntos de Acopio

**Endpoint:** `GET http://localhost:8080/api/puntos-acopio`

**Headers:**
- `Authorization`: `Bearer {tu_token}`

**Respuesta esperada:** Lista de todos los puntos de acopio con direcciones

---

### ✅ US-015: Crear Punto de Acopio (ADMIN)

**Endpoint:** `POST http://localhost:8080/api/puntos-acopio`

**Headers:**
- `Authorization`: `Bearer {token_admin}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "nombre": "Punto EcoMind Barranco",
  "ubicacion": "Av. Grau 567, Barranco - Referencia: Puente de los Suspiros"
}
```

---

### ✅ US-016: Editar Punto de Acopio (ADMIN)

**Endpoint:** `PUT http://localhost:8080/api/puntos-acopio/1`

**Headers:**
- `Authorization`: `Bearer {token_admin}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "nombre": "Punto EcoMind Lima Centro ACTUALIZADO",
  "ubicacion": "Nueva dirección actualizada"
}
```

---

### ✅ US-017: Eliminar Punto de Acopio (ADMIN)

**Endpoint:** `DELETE http://localhost:8080/api/puntos-acopio/6`

**Headers:**
- `Authorization`: `Bearer {token_admin}`

---

## 🎉 **EVENTOS AMBIENTALES**

### ✅ US-004: Consultar Eventos Próximos (Ordenados)

**Endpoint:** `GET http://localhost:8080/api/eventos/proximos`

**Headers:**
- `Authorization`: `Bearer {tu_token}`

**Respuesta esperada:** Lista de eventos ordenados por fecha (más próximos primero)

---

### ✅ US-008: Inscribirse en un Evento

**Endpoint:** `POST http://localhost:8080/api/eventos/1/inscribirse`

**Headers:**
- `Authorization`: `Bearer {tu_token}`

---

### ✅ US-009: Crear Evento (ORGANIZADOR)

**Endpoint:** `POST http://localhost:8080/api/eventos`

**Headers:**
- `Authorization`: `Bearer {token_organizador}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "nombreEvento": "Nuevo Taller de Reciclaje",
  "descripcion": "Aprende técnicas avanzadas de reciclaje",
  "fechaHora": "2025-12-25T10:00:00",
  "lugar": "Centro Cultural Lima, Av. Principal 123",
  "tipoActividad": "Taller",
  "recomendaciones": "Traer materiales reciclables",
  "organizador": {
    "id": 1
  },
  "activo": true
}
```

---

### ✅ US-010: Editar Evento (ORGANIZADOR)

**Endpoint:** `PUT http://localhost:8080/api/eventos/1`

**Headers:**
- `Authorization`: `Bearer {token_organizador}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "nombreEvento": "Limpieza de Playas - ACTUALIZADO",
  "descripcion": "Nueva descripción actualizada del evento",
  "fechaHora": "2025-11-15T09:00:00",
  "lugar": "Costa Verde, Miraflores",
  "tipoActividad": "Limpieza",
  "recomendaciones": "Recomendaciones actualizadas",
  "organizador": {
    "id": 1
  },
  "activo": true
}
```

---

### ✅ US-011: Eliminar Evento (ORGANIZADOR)

**Endpoint:** `DELETE http://localhost:8080/api/eventos/8`

**Headers:**
- `Authorization`: `Bearer {token_organizador}`

---

## 🎁 **RECOMPENSAS**

### ✅ US-005: Visualizar Catálogo de Recompensas

**Endpoint:** `GET http://localhost:8080/api/recompensas`

**Headers:**
- `Authorization`: `Bearer {tu_token}`

---

### ✅ US-012: Crear Recompensa (ADMIN)

**Endpoint:** `POST http://localhost:8080/api/recompensas`

**Headers:**
- `Authorization`: `Bearer {token_admin}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "nombreRecompensa": "Nueva Botella Ecológica Premium",
  "descripcion": "Botella de titanio de 1 litro",
  "puntosRequeridos": 300,
  "categoria": "Productos Premium",
  "stock": 20,
  "disponible": true
}
```

---

### ✅ US-013: Editar Recompensa (ADMIN)

**Endpoint:** `PUT http://localhost:8080/api/recompensas/1`

**Headers:**
- `Authorization`: `Bearer {token_admin}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "nombreRecompensa": "Botella Térmica Ecológica MEJORADA",
  "descripcion": "Descripción actualizada",
  "puntosRequeridos": 140,
  "categoria": "Productos Sostenibles",
  "stock": 60,
  "disponible": true
}
```

---

### ✅ US-014: Eliminar Recompensa (ADMIN)

**Endpoint:** `DELETE http://localhost:8080/api/recompensas/15`

**Headers:**
- `Authorization`: `Bearer {token_admin}`

---

### ✅ US-018: Ordenar Recompensas de Menor a Mayor (ASC)

**Endpoint:** `GET http://localhost:8080/api/recompensas/ordenar/asc`

**Headers:**
- `Authorization`: `Bearer {tu_token}`

**Respuesta esperada:** Recompensas ordenadas desde la de menos puntos (40) hasta la de más puntos (250)

---

### ✅ US-019: Ordenar Recompensas de Mayor a Menor (DESC)

**Endpoint:** `GET http://localhost:8080/api/recompensas/ordenar/desc`

**Headers:**
- `Authorization`: `Bearer {tu_token}`

**Respuesta esperada:** Recompensas ordenadas desde la de más puntos (250) hasta la de menos puntos (40)

---

### ✅ US-006: Canjear Recompensas

**Endpoint:** `POST http://localhost:8080/api/canjes`

**Headers:**
- `Authorization`: `Bearer {tu_token}`
- `Content-Type`: `application/json`

**Body (raw - JSON):**
```json
{
  "idUsuario": {
    "id": 2
  },
  "idRecompensa": {
    "id": 2
  },
  "fechaCanje": "2025-10-03T15:30:00",
  "puntosUtilizados": 50,
  "estado": "Pendiente"
}
```

---

## 📊 **CREDENCIALES DE PRUEBA**

| Username | Password | Rol | Descripción |
|----------|----------|-----|-------------|
| `admin` | `password123` | ADMIN | Puede gestionar recompensas y puntos de acopio |
| `user1` | `password123` | USER | Usuario con 250 puntos |
| `user2` | `password123` | USER | Usuario con 150 puntos |
| `organizador1` | `password123` | ORGANIZADOR | Puede gestionar eventos |
| `organizador2` | `password123` | ORGANIZADOR | Puede gestionar eventos |

---

## 🎯 **FLUJO COMPLETO DE PRUEBA**

### 1️⃣ **Login como ADMIN**
```
POST /api/auth/login
{
  "username": "admin",
  "password": "password123"
}
```

### 2️⃣ **Ver Recompensas Ordenadas (US-018)**
```
GET /api/recompensas/ordenar/asc
Authorization: Bearer {token}
```

### 3️⃣ **Crear Nueva Recompensa (US-012)**
```
POST /api/recompensas
Authorization: Bearer {token}
Body: { nueva recompensa }
```

### 4️⃣ **Login como USER**
```
POST /api/auth/login
{
  "username": "user1",
  "password": "password123"
}
```

### 5️⃣ **Ver Catálogo de Recompensas (US-005)**
```
GET /api/recompensas
Authorization: Bearer {token}
```

### 6️⃣ **Canjear Recompensa (US-006)**
```
POST /api/canjes
Authorization: Bearer {token}
Body: { datos del canje }
```

### 7️⃣ **Ver Eventos Próximos (US-004)**
```
GET /api/eventos/proximos
Authorization: Bearer {token}
```

### 8️⃣ **Login como ORGANIZADOR**
```
POST /api/auth/login
{
  "username": "organizador1",
  "password": "password123"
}
```

### 9️⃣ **Crear Nuevo Evento (US-009)**
```
POST /api/eventos
Authorization: Bearer {token}
Body: { datos del evento }
```

### 🔟 **Editar Evento (US-010)**
```
PUT /api/eventos/1
Authorization: Bearer {token}
Body: { datos actualizados }
```

---

## ✅ **VERIFICACIÓN DE TODAS LAS US**

- [ ] US-001: Registro de usuario
- [ ] US-002: Registrar reciclaje
- [ ] US-003: Ver puntos de acopio
- [ ] US-004: Ver eventos ordenados
- [ ] US-005: Ver catálogo de recompensas
- [ ] US-006: Canjear recompensas
- [ ] US-007: Login
- [ ] US-008: Inscribirse a evento
- [ ] US-009: Crear evento (ORGANIZADOR)
- [ ] US-010: Editar evento (ORGANIZADOR)
- [ ] US-011: Eliminar evento (ORGANIZADOR)
- [ ] US-012: Crear recompensa (ADMIN)
- [ ] US-013: Editar recompensa (ADMIN)
- [ ] US-014: Eliminar recompensa (ADMIN)
- [ ] US-015: Crear punto de acopio (ADMIN)
- [ ] US-016: Editar punto de acopio (ADMIN)
- [ ] US-017: Eliminar punto de acopio (ADMIN)
- [ ] US-018: Ordenar recompensas ASC
- [ ] US-019: Ordenar recompensas DESC

---

## 🚨 **ERRORES COMUNES**

### Error 401 Unauthorized
- ✅ Verifica que el token esté en el header `Authorization: Bearer {token}`
- ✅ El token expira en 24 horas, genera uno nuevo con login

### Error 403 Forbidden
- ✅ El usuario no tiene el rol necesario
- ✅ Usa `admin` para operaciones ADMIN
- ✅ Usa `organizador1` para operaciones de ORGANIZADOR

### Error 500 Internal Server Error
- ✅ Verifica que ejecutaste el script SQL de datos iniciales
- ✅ Verifica que las tablas existen en la base de datos
- ✅ Revisa los logs del servidor

---

**¡Listo para probar las 19 User Stories! 🎉**
