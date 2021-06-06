package com.ecs.backend.repositories;

import com.ecs.backend.model.Application;
import com.ecs.backend.model.ApplicationIdUsingIdclass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationIdUsingIdclass> {
}
