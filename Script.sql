CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	nombre VARCHAR (50) NOT NULL,
	email VARCHAR (50) NOT NULL,
	telefono VARCHAR (50) NOT NULL,
	direccion VARCHAR (50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE prestamo(
	id SERIAL PRIMARY KEY,
	monto DECIMAL (15,2) NOT NULL,
	interes DECIMAL (3,2) NOT NULL,
	duracion_meses INTEGER NOT NULL,
	estado VARCHAR (50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	id_cliente INTEGER REFERENCES cliente(id) NOT NULL
);

CREATE TABLE historial_prestamo(
	id SERIAL PRIMARY KEY,
	id_prestamo INTEGER REFERENCES prestamo(id) NOT NULL,
	monto DECIMAL (15,2) NOT NULL,
	estado VARCHAR (50) NOT NULL,
	fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO public.cliente(nombre, email, telefono, direccion)
VALUES ('Juan Pérez', 'juan.perez@example.com', '123456789', 'Calle Falsa 123');

INSERT INTO public.cliente(nombre, email, telefono, direccion)
VALUES ('María Gómez', 'maria.gomez@example.com', '987654321', 'Avenida Siempre Viva 456');

INSERT INTO public.cliente(nombre, email, telefono, direccion)
VALUES ('Carlos López', 'carlos.lopez@example.com', '555123456', 'Boulevard de los Sueños 789');

INSERT INTO public.cliente(nombre, email, telefono, direccion)
VALUES ('Ana Martínez', 'ana.martinez@example.com', '444987654', 'Paseo de la Esperanza 321');

INSERT INTO public.cliente(nombre, email, telefono, direccion)
VALUES ('Luis Torres', 'luis.torres@example.com', '666543210', 'Calle de la Libertad 654');