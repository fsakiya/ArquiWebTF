# 🚀 CÓDIGO COMPLETO PARA COPIAR Y PEGAR

Este archivo contiene **TODO el código restante** que necesitas crear para completar las 19 User Stories.

---

## 📦 DTOs DE AUTENTICACIÓN

### 1. `LoginRequest.java`
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/dto/LoginRequest.java`

```java
package com.ecominds.tf_arquiweb.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
```

### 2. `LoginResponse.java`
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/dto/LoginResponse.java`

```java
package com.ecominds.tf_arquiweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private List<String> roles;
}
```

### 3. `RegisterRequest.java`
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/dto/RegisterRequest.java`

```java
package com.ecominds.tf_arquiweb.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String nombre;
}
```

---

## 🎮 CONTROLLER DE AUTENTICACIÓN

### `AuthController.java`
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/controller/AuthController.java`

```java
package com.ecominds.tf_arquiweb.controller;

import com.ecominds.tf_arquiweb.dto.LoginRequest;
import com.ecominds.tf_arquiweb.dto.LoginResponse;
import com.ecominds.tf_arquiweb.dto.RegisterRequest;
import com.ecominds.tf_arquiweb.entidades.Role;
import com.ecominds.tf_arquiweb.entidades.Users;
import com.ecominds.tf_arquiweb.repositorios.RoleRepository;
import com.ecominds.tf_arquiweb.repositorios.UserRepository;
import com.ecominds.tf_arquiweb.securities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // US-007: Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String role = roles.isEmpty() ? "USER" : roles.get(0);
            String token = jwtUtil.generateToken(loginRequest.getUsername(), role);

            return ResponseEntity.ok(new LoginResponse(token, loginRequest.getUsername(), roles));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }
    }

    // US-001: Crear una cuenta
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        // Validar si el usuario ya existe
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body("El username ya está en uso");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está en uso");
        }

        // Crear nuevo usuario
        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setNombre(registerRequest.getNombre());
        user.setEnabled(true);
        user.setPuntosAcumulados(0);

        Users savedUser = userRepository.save(user);

        // Asignar rol USER por defecto
        Role role = new Role();
        role.setRol("USER");
        role.setUser(savedUser);
        roleRepository.save(role);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado exitosamente");
    }
}
```

---

## 📊 QUERIES PERSONALIZADAS EN REPOSITORIOS

### `RecompensaRepository.java` - Actualizar para US-018 y US-019
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/repositorios/RecompensaRepository.java`

```java
package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Integer> {
    
    // US-018: Ordenar de menor a mayor por puntos
    @Query("SELECT r FROM Recompensa r WHERE r.disponible = true ORDER BY r.puntosRequeridos ASC")
    List<Recompensa> findAllOrderByPuntosAsc();
    
    // US-019: Ordenar de mayor a menor por puntos
    @Query("SELECT r FROM Recompensa r WHERE r.disponible = true ORDER BY r.puntosRequeridos DESC")
    List<Recompensa> findAllOrderByPuntosDesc();
    
    // Buscar recompensas disponibles
    @Query("SELECT r FROM Recompensa r WHERE r.disponible = true")
    List<Recompensa> findAllDisponibles();
}
```

### `EventoRepository.java` - Actualizar para US-004
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/repositorios/EventoRepository.java`

```java
package com.ecominds.tf_arquiweb.repositorios;

import com.ecominds.tf_arquiweb.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    
    // US-004: Eventos ordenados por fecha (próximos primero)
    @Query("SELECT e FROM Evento e WHERE e.activo = true AND e.fechaHora >= :now ORDER BY e.fechaHora ASC")
    List<Evento> findEventosProximos(LocalDateTime now);
    
    // Buscar eventos por organizador
    @Query("SELECT e FROM Evento e WHERE e.organizador.id = :idOrganizador")
    List<Evento> findByOrganizador(Long idOrganizador);
}
```

---

## 🎯 CONTROLADORES ACTUALIZADOS

### `RecompensaController.java` - Con todas las US
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/controller/RecompensaController.java`

```java
package com.ecominds.tf_arquiweb.controller;

import com.ecominds.tf_arquiweb.dto.RecompensaDTO;
import com.ecominds.tf_arquiweb.entidades.Recompensa;
import com.ecominds.tf_arquiweb.servicios.iRecompensaServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recompensas")
@CrossOrigin(origins = "*")
public class RecompensaController {

    @Autowired
    private iRecompensaServices recompensaService;

    private ModelMapper modelMapper = new ModelMapper();

    // US-005: Visualizar catálogo de recompensas
    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<RecompensaDTO> listarRecompensas() {
        return recompensaService.list().stream()
                .map(r -> modelMapper.map(r, RecompensaDTO.class))
                .collect(Collectors.toList());
    }

    // US-012: Crear recompensa (ADMIN)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public RecompensaDTO crearRecompensa(@RequestBody RecompensaDTO dto) {
        Recompensa recompensa = modelMapper.map(dto, Recompensa.class);
        Recompensa saved = recompensaService.insert(recompensa);
        return modelMapper.map(saved, RecompensaDTO.class);
    }

    // US-013: Editar recompensa (ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RecompensaDTO editarRecompensa(@PathVariable Integer id, @RequestBody RecompensaDTO dto) {
        dto.setId(id);
        Recompensa recompensa = modelMapper.map(dto, Recompensa.class);
        Recompensa updated = recompensaService.update(recompensa);
        return modelMapper.map(updated, RecompensaDTO.class);
    }

    // US-014: Eliminar recompensa (ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminarRecompensa(@PathVariable Integer id) {
        recompensaService.delete(id);
    }

    // US-018: Ordenar de menor a mayor
    @GetMapping("/ordenar/asc")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<RecompensaDTO> ordenarPorPuntosAsc() {
        return recompensaService.listarOrdenadoPorPuntosAsc().stream()
                .map(r -> modelMapper.map(r, RecompensaDTO.class))
                .collect(Collectors.toList());
    }

    // US-019: Ordenar de mayor a menor
    @GetMapping("/ordenar/desc")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<RecompensaDTO> ordenarPorPuntosDesc() {
        return recompensaService.listarOrdenadoPorPuntosDesc().stream()
                .map(r -> modelMapper.map(r, RecompensaDTO.class))
                .collect(Collectors.toList());
    }
}
```

---

## 📝 SERVICIOS ACTUALIZADOS

### `iRecompensaServices.java` - Interface
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/intefaces/iRecompensaServices.java`

```java
package com.ecominds.tf_arquiweb.intefaces;

import com.ecominds.tf_arquiweb.entidades.Recompensa;

import java.util.List;

public interface iRecompensaServices {
    List<Recompensa> list();
    Recompensa insert(Recompensa recompensa);
    Recompensa update(Recompensa recompensa);
    void delete(Integer id);
    List<Recompensa> listarOrdenadoPorPuntosAsc();
    List<Recompensa> listarOrdenadoPorPuntosDesc();
}
```

### `RecompensaServicesImplement.java` - Implementación
**Ruta:** `src/main/java/com/ecominds/tf_arquiweb/servicios/RecompensaServicesImplement.java`

```java
package com.ecominds.tf_arquiweb.servicios;

import com.ecominds.tf_arquiweb.entidades.Recompensa;
import com.ecominds.tf_arquiweb.intefaces.iRecompensaServices;
import com.ecominds.tf_arquiweb.repositorios.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecompensaServicesImplement implements iRecompensaServices {

    @Autowired
    private RecompensaRepository repository;

    @Override
    public List<Recompensa> list() {
        return repository.findAll();
    }

    @Override
    public Recompensa insert(Recompensa recompensa) {
        return repository.save(recompensa);
    }

    @Override
    public Recompensa update(Recompensa recompensa) {
        return repository.save(recompensa);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Recompensa> listarOrdenadoPorPuntosAsc() {
        return repository.findAllOrderByPuntosAsc();
    }

    @Override
    public List<Recompensa> listarOrdenadoPorPuntosDesc() {
        return repository.findAllOrderByPuntosDesc();
    }
}
```

---

## 🗄️ SCRIPT SQL DE DATOS INICIALES

### `data.sql`
**Ruta:** `src/main/resources/data.sql`

```sql
-- ============================================
-- DATOS INICIALES PARA ECOMIND
-- Password: "password123" (encriptado con BCrypt)
-- ============================================

-- Usuarios
INSERT INTO users (username, email, password, nombre, enabled, puntos_acumulados) VALUES
('admin', 'admin@ecomind.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 'Administrador EcoMind', true, 0),
('user1', 'user1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 'Juan Pérez', true, 250),
('organizador1', 'org1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 'María López', true, 0)
ON CONFLICT DO NOTHING;

-- Roles
INSERT INTO roles (rol, user_id) VALUES
('ADMIN', 1),
('USER', 2),
('ORGANIZADOR', 3)
ON CONFLICT DO NOTHING;

-- Materiales de Reciclaje
INSERT INTO material_de_reciclaje (nombre, descripcion) VALUES
('Plástico PET', 'Botellas de plástico transparente'),
('Papel y Cartón', 'Papel blanco, periódicos, cajas de cartón'),
('Vidrio', 'Botellas y envases de vidrio'),
('Aluminio', 'Latas de bebidas')
ON CONFLICT DO NOTHING;

-- Puntos de Acopio
INSERT INTO punto_de_acopio (nombre, ubicacion) VALUES
('Punto Lima Centro', 'Av. Arequipa 1234, Cercado de Lima'),
('Punto Miraflores', 'Av. Larco 890, Miraflores'),
('Punto San Isidro', 'Av. Javier Prado 456, San Isidro')
ON CONFLICT DO NOTHING;

-- Recompensas
INSERT INTO recompensas (nombre_recompensa, descripcion, puntos_requeridos, categoria, stock, disponible) VALUES
('Botella Térmica', 'Botella de acero inoxidable 750ml', 100, 'Productos', 50, true),
('Bolsa Ecológica', 'Set de 3 bolsas reutilizables', 50, 'Productos', 100, true),
('Planta Suculenta', 'Planta decorativa de bajo mantenimiento', 80, 'Plantas', 30, true),
('Descuento 20% en Tienda Verde', 'Cupón de descuento', 150, 'Cupones', 200, true),
('Libro de Reciclaje', 'Guía práctica de reciclaje en casa', 120, 'Educación', 40, true)
ON CONFLICT DO NOTHING;

-- Organizadores
INSERT INTO organizadores (nombre, email, telefono, organizacion) VALUES
('Fundación Eco Lima', 'contacto@ecolima.org', '987654321', 'Fundación Eco Lima'),
('Green Team Perú', 'info@greenteam.pe', '912345678', 'Green Team Perú')
ON CONFLICT DO NOTHING;

-- Eventos
INSERT INTO eventos (nombre_evento, descripcion, fecha_hora, lugar, tipo_actividad, recomendaciones, organizador_id, activo) VALUES
('Limpieza de Playas', 'Jornada de limpieza en Costa Verde', '2025-11-15 09:00:00', 'Costa Verde, Miraflores', 'Limpieza', 'Traer protector solar y agua', 1, true),
('Taller de Compostaje', 'Aprende a hacer compost en casa', '2025-11-20 15:00:00', 'Parque Kennedy, Miraflores', 'Taller', 'Inscripción previa requerida', 1, true),
('Plantación de Árboles', 'Reforestación en parque local', '2025-12-01 08:00:00', 'Parque de las Leyendas', 'Plantación', 'Ropa cómoda y guantes', 2, true)
ON CONFLICT DO NOTHING;
```

---

## ✅ **CHECKLIST COMPLETO**

- [x] `pom.xml` actualizado con dependencias JWT
- [x] `application.properties` configurado
- [x] Entidades: Users, Role creadas
- [x] JwtUtil.java
- [x] JwtRequestFilter.java
- [x] JwtAuthenticationEntryPoint.java
- [x] UserDetailsServiceImpl.java
- [x] WebSecurityConfig.java
- [x] UserRepository.java
- [x] RoleRepository.java
- [ ] **Copia y pega** los DTOs: LoginRequest, LoginResponse, RegisterRequest
- [ ] **Copia y pega** AuthController.java
- [ ] **Copia y pega** queries en RecompensaRepository y EventoRepository
- [ ] **Copia y pega** RecompensaController actualizado
- [ ] **Copia y pega** Servicios de Recompensa actualizados
- [ ] **Copia y pega** data.sql en resources

---

## 🚀 **EJECUTAR EL PROYECTO**

```bash
cd "ArquiWebTF-master definitivo/ArquiWebTF-master"
mvn clean install
mvn spring-boot:run
```

## 🧪 **PROBAR ENDPOINTS**

1. **Registro:**
```
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "nombre": "Usuario Test"
}
```

2. **Login:**
```
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "password123"
}
```

3. **Usar token en otros endpoints:**
```
GET http://localhost:8080/api/recompensas
Authorization: Bearer {tu_token_aqui}
```

---

**¡TODO LISTO! Ahora solo copia y pega los archivos marcados con [ ] en el checklist! 🎉**
