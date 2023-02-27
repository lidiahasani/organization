create table punonjes_rol
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_punonjes int NOT NULL,
    id_rol int NOT NULL,
    FOREIGN KEY (id_punonjes) references punonjes (id),
    FOREIGN KEY (id_rol) references rol (id)
);