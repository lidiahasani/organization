insert into role(name)
values('EMPLOYEE');

insert into role(name)
values('PROJECT_MANAGER');

insert into role(name)
values('DEPARTMENT_MANAGER');

insert into role(name)
values('ADMIN');

--set @role_id = last_insert_id();
--
--insert into employee(name, email, password)
--values('admin', 'admin@example.com', '$2a$12$vj/je0eQQQS/yJTXTvqYTulI.bGNYDwbFEfQWsJT4Jy.edvKvUykO');
--
--set @employee_id = last_insert_id();
--
--insert into employee_role(id_employee, id_role)
--values(@employee_id, @role_id);
