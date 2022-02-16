INSERT INTO clientes(nombre,apellido,email,telefono) VALUES( "Jose","Perez","jp@email.com",454241);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Maria","Walker","mw@email.com",232323);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Juana","Diaz","jd@email.com",3454545);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Juan","Sanchez","js@email.com",676767);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Ramon","Valdez","rv@email.com",787878);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Luis","Colina","lc@email.com",78787878);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Felix","Cardozo","fc@email.com",787878);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Martin","Araujo","ma@email.com",899889);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("David","Ojeda","do@email.com",7886767);
INSERT INTO clientes(nombre,apellido,email,telefono) VALUES("Daniel","Gues","dg@email.com",4545445);


INSERT INTO usuarios (username,password,enabled) VALUES ('rolando','$2a$10$VTMecMi.QwIynlpyuBHAAenhz9Wg2fEk4VbaOlXn.2xKkCJTMf75u',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('admin','$2a$10$nSgo1TPH4IQRro7HkVqrBO.cNC1cXrW5Xyhs5u/NwkjeEUh9Bo65G',1);

INSERT INTO roles (nombre) VALUES('ROLE_USER');
INSERT INTO roles (nombre) VALUES('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,role_id) VALUES(2,1);