package main.ex.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.ex.spring.entity.ERole;
import main.ex.spring.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
