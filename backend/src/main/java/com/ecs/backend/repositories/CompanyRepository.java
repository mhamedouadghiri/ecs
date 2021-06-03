package com.ecs.backend.repositories;

import com.ecs.backend.model.Company;
import com.ecs.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {


    User findByEmail(String email);
}
