package com.ecs.backend.repositories;

import com.ecs.backend.model.Company;
import com.ecs.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    User findByEmail(String email);
}
