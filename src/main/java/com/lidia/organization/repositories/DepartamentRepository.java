package com.lidia.organization.repositories;

import com.lidia.organization.model.Departament;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartamentRepository extends CrudRepository<Departament, Long> {


}
