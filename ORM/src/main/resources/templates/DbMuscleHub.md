USE proyect;

-- Crear la tabla Role
CREATE TABLE Role (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear la tabla User
CREATE TABLE User (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Crear la tabla UserRole para la asignación de roles
CREATE TABLE UserRole (
user_id INT NOT NULL,
role_id INT NOT NULL,
PRIMARY KEY (user_id, role_id),
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (role_id) REFERENCES Role(id)
);

-- Crear la tabla Session para las sesiones físicas
CREATE TABLE PhysicalSession(
id INT AUTO_INCREMENT PRIMARY KEY,
trainer_id INT NOT NULL,
name VARCHAR(100) NOT NULL,
description TEXT,
image VARCHAR(255),
capacity INT NOT NULL,
start_time DATETIME NOT NULL,
end_time DATETIME NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (trainer_id) REFERENCES User(id)
);

-- Crear la tabla Session para las sesiones virtuales
CREATE TABLE VirtualSession(
id INT AUTO_INCREMENT PRIMARY KEY,
trainer_id INT NOT NULL,
name VARCHAR(100) NOT NULL,
description TEXT,
image VARCHAR(255),
capacity INT NOT NULL,
start_time DATETIME NOT NULL,
end_time DATETIME NOT NULL,
link VARCHAR(255) NOT NULL,  -- Agregamos un campo para el link de la clase virtual
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (trainer_id) REFERENCES User(id)
);

-- Crear la tabla Enrollment para sesiones físicas
CREATE TABLE PhysicalEnrollment (
id INT AUTO_INCREMENT PRIMARY KEY,
session_id INT NOT NULL,
user_id INT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (session_id) REFERENCES PhysicalSession(id),
FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Crear la tabla Enrollment para sesiones virtuales
CREATE TABLE VirtualEnrollment (
id INT AUTO_INCREMENT PRIMARY KEY,
session_id INT NOT NULL,
user_id INT NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (session_id) REFERENCES VirtualSession(id),
FOREIGN KEY (user_id) REFERENCES User(id)
);

-- Crear la tabla CustomSession para solicitudes de clases personalizadas
CREATE TABLE CustomSession (
id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT NOT NULL,
trainer_id INT NOT NULL,
description TEXT NOT NULL,
requested_date DATETIME NOT NULL,
status ENUM('Pendiente', 'Aceptada', 'Rechazada') NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (user_id) REFERENCES User(id),
FOREIGN KEY (trainer_id) REFERENCES User(id)
);

-- Insertar roles básicos en la tabla Role
INSERT INTO Role (name) VALUES ('Admin'), ('Trainer'), ('User');
