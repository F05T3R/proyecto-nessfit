INSERT INTO roles (id,nombre) VALUES (1,'ADMINISTRADOR');
INSERT INTO roles (id,nombre) VALUES (2,'ADMINISTRATIVO');
INSERT INTO roles (id,nombre) VALUES (3,'CLIENTE');

INSERT INTO usuarios (nombre,rut,contrasena,email,estado,apellido,telefono,id_rol) VALUES ('Administrador','17977139K','$2a$10$ZuvqMWbD9ULmJbjHCAekgeDY.VF7RfZbDMrF4n2Gp0/fcQcjOXJ6m','admin@nessfit.cl',1,'Administrador',123456789,1);