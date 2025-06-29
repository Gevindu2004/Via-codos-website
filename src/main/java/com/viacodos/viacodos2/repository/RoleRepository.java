package com.viacodos.viacodos2.repository;

import com.viacodos.viacodos2.entity.ERole;
import com.viacodos.viacodos2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
} 