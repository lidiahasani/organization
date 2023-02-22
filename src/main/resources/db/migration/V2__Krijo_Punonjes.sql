create table punonjes
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    emer VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    id_departament int,
    FOREIGN KEY (id_departament) references departament (id)
);