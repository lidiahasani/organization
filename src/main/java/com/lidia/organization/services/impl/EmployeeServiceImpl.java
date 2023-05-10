package com.lidia.organization.services.impl;

import com.lidia.organization.dto.EmployeeDto;
import com.lidia.organization.dto.RoleDto;
import com.lidia.organization.dto.TaskDto;
import com.lidia.organization.exception.EmailNotUniqueException;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.model.ERole;
import com.lidia.organization.model.Employee;
import com.lidia.organization.model.Role;
import com.lidia.organization.repositories.*;
import com.lidia.organization.services.api.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TaskRepository taskRepository;

    private final TaskServiceImpl taskService;

    private final DepartmentRepository departmentRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskRepository taskRepository, TaskServiceImpl taskService, DepartmentRepository departmentRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void create(EmployeeDto employeeDto) {
        employeeRepository.save(toEmployee().apply(employeeDto));
    }

    @Override
    public EmployeeDto read(String name){
        return employeeRepository.findByName(name).map(toEmployeeDto())
                .orElseThrow(() -> new EntityNotExistsException("Employee does not exist."));
    }

    @Override
    public List<EmployeeDto> read(){
        return employeeRepository.findAll().stream().map(toEmployeeDto()).toList();
    }

    @Override
    public void delete(int id){
        taskRepository.findByEmployeeId(id).forEach(task -> {
            task.setEmployee(null);
            taskRepository.save(task);
        });
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto){
        var employee = employeeRepository.save(toEmployeeUpdate().apply(employeeDto));
        return toEmployeeDto().apply(employee);
    }

    public Function<Employee, EmployeeDto> toEmployeeDto() {
        return employee -> {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            employeeDto.setEmail(employee.getEmail());
            Optional.ofNullable(employee.getDepartment())
                    .ifPresent(department -> employeeDto.setDepartmentId(employee.getDepartment().getId()));
            List<TaskDto> taskDtoList = employee.getTasks().stream().map(taskService.toTaskDto()).toList();
            employeeDto.setTaskDtoList(taskDtoList);
            List<RoleDto> roleDtoList = employee.getRoles().stream().map(rol -> new RoleDto(String.valueOf(rol.getName()))).toList();
            employeeDto.setRoleDtoList(roleDtoList);
            return employeeDto;
        };
    }

    public Function<EmployeeDto, Employee> toEmployee() {
        return employeeDto -> {
            Employee employee = new Employee();
            if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
                throw new EmailNotUniqueException();
            }

            employee.setName(employeeDto.getName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPassword(encoder.encode(employeeDto.getPassword()));

            if(employeeDto.getDepartmentId() != 0) {
                departmentRepository.findById(employeeDto.getDepartmentId()).
                        ifPresentOrElse(employee::setDepartment, () -> {
                            throw new EntityNotExistsException("Department does not exist.");
                        });
            }

            List<ERole> roleNames = employeeDto.getRoleDtoList()
                    .stream()
                    .map(RoleDto::name)
                    .map(ERole::valueOf)
                    .toList();

            List<Role> roles = roleRepository.findAllByNameIn(roleNames);

            employee.setRoles(roles);
            return employee;
        };
    }

    public Function<EmployeeDto, Employee> toEmployeeUpdate() {
        return employeeDto -> employeeRepository.findById(employeeDto.getId())
                .map(
                        employee -> {
                            employee.setName(employeeDto.getName());
                            if (employeeRepository.existsByEmailAndIdIsNot(employeeDto.getEmail(), employeeDto.getId())) {
                                throw new EmailNotUniqueException();
                            } else {
                                employee.setEmail(employeeDto.getEmail());
                            }
                            if (employeeDto.getDepartmentId() != 0) {
                                departmentRepository.findById(employeeDto.getDepartmentId()).
                                        ifPresentOrElse(employee::setDepartment, () -> {
                                            throw new EntityNotExistsException("Department does not exist.");
                                        });
                            }
                            List<ERole> roleNames = employeeDto.getRoleDtoList()
                                    .stream()
                                    .map(RoleDto::name)
                                    .map(ERole::valueOf)
                                    .toList();

                            List<Role> roles = roleRepository.findAllByNameIn(roleNames);

                            employee.setRoles(roles);
                            return employee;
                        }
                ).orElseThrow(() -> new EntityNotExistsException("Employee with the required id does not exist."));
    }

}
