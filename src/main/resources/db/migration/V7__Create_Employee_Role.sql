create table employee_role
(
    id int NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_employee int NOT NULL,
    id_role int NOT NULL,
    FOREIGN KEY (id_employee) references employee (id),
    FOREIGN KEY (id_role) references role (id)
);