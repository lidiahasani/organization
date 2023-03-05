update rol
set emer = 'ROLE_EMPLOYEE'
where emer = 'EMPLOYEE';

update rol
set emer = 'ROLE_PROJECT_MANAGER'
where emer = 'PROJECT_MANAGER';

update rol
set emer = 'ROLE_DEPARTMENT_MANAGER'
where emer = 'DEPARTMENT_MANAGER';

update rol
set emer = 'ROLE_ADMIN'
where emer = 'ADMIN';