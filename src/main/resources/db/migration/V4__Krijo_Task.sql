create table task
(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titull VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_projekt int,
    id_punonjes int,
    FOREIGN KEY (id_projekt) references projekt (id),
    FOREIGN KEY (id_punonjes) references punonjes (id)
);