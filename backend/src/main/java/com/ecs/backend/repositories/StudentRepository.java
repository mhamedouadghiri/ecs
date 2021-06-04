package com.ecs.backend.repositories;

import com.ecs.backend.model.Student;
import com.ecs.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    User findByEmail(String email);

    List<User> findAllBySchoolId(Long schoolId);
}
