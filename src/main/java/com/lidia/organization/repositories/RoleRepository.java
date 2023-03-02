package com.lidia.organization.repositories;

import com.lidia.organization.model.ERole;
import com.lidia.organization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAllByEmerIn(List<ERole> roleNames);

}
