package com.ecs.backend.repositories;

import com.ecs.backend.model.Student;
import com.ecs.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from student s where s.email = ?1")
    User getByEmail(String email);
}
