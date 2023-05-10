create table employee
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    id_department int,
    FOREIGN KEY (id_department) references department (id)
);