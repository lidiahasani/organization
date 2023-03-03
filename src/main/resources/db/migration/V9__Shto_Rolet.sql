insert into rol(emer)
values('EMPLOYEE');

insert into rol(emer)
values('PROJECT_MANAGER');

insert into rol(emer)
values('DEPARTMENT_MANAGER');

insert into rol(emer)
values('ADMIN');

set @rol_id = last_insert_id();

insert into punonjes(emer, email, password)
values('admin', 'admin@example.com', '$2a$12$vj/je0eQQQS/yJTXTvqYTulI.bGNYDwbFEfQWsJT4Jy.edvKvUykO');

set @punonjes_id = last_insert_id();

insert into punonjes_rol(id_punonjes, id_rol)
values(@punonjes_id, @rol_id);
