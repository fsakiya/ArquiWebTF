-- ============================================
-- DATOS INICIALES PARA ECOMIND - 19 USER STORIES
-- Ejecutar DESPUÉS de que Spring Boot haya creado las tablas
-- ============================================

-- ============================================
-- 1. USUARIOS Y ROLES (US-001, US-007)
-- Contraseñas: admin123, user123, user321, orga123, orga321
-- ============================================
INSERT INTO users (id, username, email, password, nombre, enabled, puntos_acumulados) VALUES
(1, 'admin', 'admin@ecomind.com', '$2a$12$z5ZJ.5JEKz3mD0ZY8XG3kuxw.zWGihRY.qzQEsMLPnoSc4YdeGHJO', 'Administrador EcoMind', true, 0),
(2, 'user1', 'user1@example.com', '$2a$12$Jgcy4hIgw/RIBB/rBf7bWOsslt6Ya9dLcxMNuQ77v3EYce/FCbP8S', 'Juan Pérez', true, 250),
(3, 'user2', 'user2@example.com', '$2a$12$fujmZ..44zuqo33FQkESfORl/oRDE53.aJC.9eXcB96xzbu6M.Bdi', 'Ana García', true, 150),
(4, 'organizador1', 'org1@example.com', '$2a$12$ijtKc/8r0vGTNT9/962pSuPQpAGVMjnnPVftzlpdOuVFvYae9AaZa', 'María López Organizadora', true, 0),
(5, 'organizador2', 'org2@example.com', '$2a$12$AVF4LcsltH7n6PFpD.gLgus8o6tboaCtf2JPS0CFZoxoFORPO9I0.', 'Carlos Mendoza', true, 0);

-- Reiniciar secuencia
ALTER SEQUENCE users_id_seq RESTART WITH 6;

INSERT INTO roles (id, rol, user_id) VALUES
(1, 'ADMIN', 1),
(2, 'USER', 2),
(3, 'USER', 3),
(4, 'ORGANIZADOR', 4),
(5, 'ORGANIZADOR', 5);

-- Reiniciar secuencia
ALTER SEQUENCE roles_id_seq RESTART WITH 6;

-- ============================================
-- 2. MATERIALES DE RECICLAJE (US-002)
-- ============================================

INSERT INTO material_de_reciclaje (id_materiales, nombre, descripcion) VALUES
(1, 'Plástico PET', 'Botellas de plástico transparente - Código de reciclaje 1'),
(2, 'Papel y Cartón', 'Papel blanco, periódicos, revistas, cajas de cartón'),
(3, 'Vidrio', 'Botellas y envases de vidrio transparente y de color'),
(4, 'Aluminio', 'Latas de bebidas, papel aluminio'),
(5, 'Plástico HDPE', 'Envases de productos de limpieza - Código 2'),
(6, 'Tetra Pak', 'Envases de leche y jugos');

-- Reiniciar secuencia
ALTER SEQUENCE material_de_reciclaje_id_materiales_seq RESTART WITH 7;

-- ============================================
-- 3. PUNTOS DE ACOPIO (US-003, US-015, US-016, US-017)
-- ============================================

INSERT INTO punto_de_acopio (id_acopio, nombre, ubicacion) VALUES
(1, 'Punto EcoMind Lima Centro', 'Av. Arequipa 1234, Cercado de Lima - Referencia: Frente al Parque de la Reserva'),
(2, 'Punto EcoMind Miraflores', 'Av. Larco 890, Miraflores - Referencia: A 2 cuadras del Parque Kennedy'),
(3, 'Punto EcoMind San Isidro', 'Av. Javier Prado 456, San Isidro - Referencia: Centro Financiero'),
(4, 'Punto EcoMind Surco', 'Av. Benavides 2345, Santiago de Surco - Referencia: Centro Comercial Chacarilla'),
(5, 'Punto EcoMind La Molina', 'Av. La Universidad 567, La Molina - Referencia: Campus PUCP'),
(6, 'Punto EcoMind Callao', 'Av. Colonial 789, Callao - Referencia: Plaza San José');

-- Reiniciar secuencia
ALTER SEQUENCE punto_de_acopio_id_acopio_seq RESTART WITH 7;

-- ============================================
-- 4. ORGANIZADORES (US-009, US-010, US-011)
-- ============================================

INSERT INTO organizador (id_organizador, nombre) VALUES
(1, 'Fundación Eco Lima'),
(2, 'Green Team Perú'),
(3, 'Lima Verde'),
(4, 'Recicladores Unidos');

-- Reiniciar secuencia
ALTER SEQUENCE organizador_id_organizador_seq RESTART WITH 5;

-- ============================================
-- 5. EVENTOS AMBIENTALES (US-004, US-008, US-009, US-010, US-011)
-- Campos: id_evento, nombre, fecha, hora, lugar, descripcion, id_organizador
-- ============================================

INSERT INTO evento (id_evento, nombre, fecha, hora, lugar, descripcion, id_organizador) VALUES
(1, 'Limpieza de Playas Costa Verde', '2025-11-15', '09:00:00', 'Costa Verde, Miraflores', 'Jornada de limpieza comunitaria en las playas. Traer protector solar y guantes.', 1),
(2, 'Taller de Compostaje Casero', '2025-11-20', '15:00:00', 'Parque Kennedy, Miraflores', 'Aprende técnicas de compostaje casero. Incluye kit básico.', 1),
(3, 'Plantación de Árboles', '2025-12-01', '08:00:00', 'Parque de las Leyendas', 'Reforestación con especies nativas. Ropa cómoda y guantes de jardinería.', 2),
(4, 'Feria de Reciclaje Creativo', '2025-12-05', '10:00:00', 'Plaza San Martín', 'Productos hechos con materiales reciclados. Talleres creativos.', 3),
(5, 'Charla: Economía Circular', '2025-12-10', '18:00:00', 'Auditorio Miraflores', 'Conferencia sobre economía circular en el hogar. Certificado incluido.', 2),
(6, 'Maratón de Reciclaje Familiar', '2025-12-15', '09:00:00', 'Parque de la Amistad', 'Juegos educativos y competencias de reciclaje para toda la familia.', 3),
(7, 'Limpieza del Río Rímac', '2025-12-20', '07:00:00', 'Puente Nuevo, Lima', 'Limpieza de riberas. Traer botas de agua y guantes gruesos.', 4),
(8, 'Taller de Bolsas Ecológicas', '2026-01-10', '14:00:00', 'Centro Cultural San Isidro', 'Confección de bolsas reutilizables. Materiales incluidos.', 1);

-- Reiniciar secuencia
ALTER SEQUENCE evento_id_evento_seq RESTART WITH 9;

-- ============================================
-- 6. RECOMPENSAS (US-005, US-006, US-012, US-013, US-014, US-018, US-019)
-- Campos: id_recompensa, descripcion, requisito
-- ============================================

INSERT INTO recompensa (id_recompensa, descripcion, requisito) VALUES
(1, 'Botella Térmica Ecológica 750ml - Acero inoxidable', '150 puntos'),
(2, 'Set de 3 Bolsas Reutilizables de tela orgánica', '50 puntos'),
(3, 'Planta Suculenta con Maceta de cerámica reciclada', '80 puntos'),
(4, 'Cupón 20% descuento en Tienda Verde', '100 puntos'),
(5, 'Libro: Guía Práctica de Reciclaje (200 páginas)', '120 puntos'),
(6, 'Kit de 4 Cepillos de Bambú biodegradable', '60 puntos'),
(7, 'Taza Térmica Reutilizable 350ml con tapa', '90 puntos'),
(8, 'Cupón 50% descuento en Taller de Compostaje', '180 puntos'),
(9, 'Set de Utensilios Portátiles de bambú con estuche', '110 puntos'),
(10, 'Paquete de 10 Semillas Orgánicas para huerto', '40 puntos'),
(11, 'Pack de 3 Jabones Artesanales Ecológicos', '70 puntos'),
(12, 'Entrada Doble para Charla Ambiental', '130 puntos'),
(13, 'Cupón 30% descuento en Productos de Limpieza Eco', '85 puntos'),
(14, 'Set de 3 Contenedores para Reciclaje en casa', '200 puntos'),
(15, 'Curso Online de Vida Sostenible (4 semanas)', '250 puntos');

-- Reiniciar secuencia
ALTER SEQUENCE recompensa_id_recompensa_seq RESTART WITH 16;

-- ============================================
-- 7. DATOS DE USUARIO (tabla usuario - negocio)
-- ⚠️ IMPORTANTE: Insertar PRIMERO antes de reciclajes y canjes
-- Sincronizado con users (autenticación)
-- ============================================

INSERT INTO usuario (id_usuario, nombre, apellido, correo, contrasena, peso_recolectado, puntos) VALUES
(1, 'Administrador', 'EcoMind', 'admin@ecomind.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 0.0, 0),
(2, 'Juan', 'Pérez', 'user1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 8.0, 250),
(3, 'Ana', 'García', 'user2@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 6.0, 150),
(4, 'María López', 'Organizadora', 'org1@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 0.0, 0),
(5, 'Carlos', 'Mendoza', 'org2@example.com', '$2a$10$N9qo8uLOickgx2ZMRZoMye5po/ZfFCdXqXf5ZYCLTlpbHfECJWHs6', 0.0, 0);

-- Reiniciar secuencia
ALTER SEQUENCE usuario_id_usuario_seq RESTART WITH 6;

-- ============================================
-- 8. DATOS DE EJEMPLO PARA RECICLAJES (US-002)
-- Campos: id_reciclaje, fecha, hora, peso, id_usuario, id_materiales, id_acopio
-- ============================================

INSERT INTO reciclaje (id_reciclaje, fecha, hora, peso, id_usuario, id_materiales, id_acopio) VALUES
(1, '2025-10-01', '10:30:00', 5.5, 1, 1, 1),
(2, '2025-10-01', '11:00:00', 3.2, 1, 2, 1),
(3, '2025-10-02', '09:15:00', 4.8, 2, 1, 2),
(4, '2025-10-02', '14:20:00', 2.5, 2, 4, 2),
(5, '2025-10-03', '08:45:00', 6.0, 3, 3, 3);

-- Reiniciar secuencia
ALTER SEQUENCE reciclaje_id_reciclaje_seq RESTART WITH 6;

-- ============================================
-- 9. DATOS DE EJEMPLO PARA CANJES (US-006)
-- Campos: id_canje, fecha, costo, id_usuario, id_recompensa
-- ============================================

INSERT INTO canje (id_canje, fecha, costo, id_usuario, id_recompensa) VALUES
(1, '2025-10-01', 50, 1, 2),
(2, '2025-10-02', 40, 2, 10),
(3, '2025-10-03', 60, 3, 6);

-- Reiniciar secuencia
ALTER SEQUENCE canje_id_canje_seq RESTART WITH 4;

-- ============================================
-- ✅ DATOS LISTOS PARA PROBAR LAS 19 USER STORIES
-- Script adaptado a la estructura real de la base de datos
-- ============================================
