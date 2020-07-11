package com.crickbean.application.repositories;

import com.crickbean.application.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AuthorityRepository extends JpaRepository<Role, Long> {
	 Role findByRoleName(String roleName);
}