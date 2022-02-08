package com.travelAppSpringBoot.repository;

import com.travelAppSpringBoot.enums.ERole;
import com.travelAppSpringBoot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
