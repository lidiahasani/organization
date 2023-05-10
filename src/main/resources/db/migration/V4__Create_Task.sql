create table task
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_project int,
    id_employee int,
    FOREIGN KEY (id_project) references project (id),
    FOREIGN KEY (id_employee) references employee (id)
);