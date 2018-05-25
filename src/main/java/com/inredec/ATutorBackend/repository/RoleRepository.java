package com.inredec.ATutorBackend.repository;

import com.inredec.ATutorBackend.model.Role;
import com.inredec.ATutorBackend.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
