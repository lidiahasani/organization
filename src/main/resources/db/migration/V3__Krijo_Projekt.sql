create table projekt
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titull VARCHAR(50) NOT NULL,
    data_nisje DATE NOT NULL,
    data_perfundim DATE,
    status VARCHAR(50) NOT NULL,
    id_departament int,
    FOREIGN KEY (id_departament) references departament (id)
);