package com.lidia.organization.repositories;

import com.lidia.organization.model.ERole;
import com.lidia.organization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByEmer(ERole emer);

    List<Role> findAllByEmerIn(List<ERole> roleNames);

}
