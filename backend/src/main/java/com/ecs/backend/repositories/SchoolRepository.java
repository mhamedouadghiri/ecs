package com.ecs.backend.repositories;

import com.ecs.backend.model.School;
import com.ecs.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {

    @Query("select s from school s where s.email = ?1")
    User getByEmail(String email);
}
