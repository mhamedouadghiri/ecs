package com.ecs.backend.repositories;

import com.ecs.backend.model.Application;
import com.ecs.backend.model.ApplicationIdUsingIdclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationIdUsingIdclass> {
    ResponseEntity<?> findAllByInternship(Long offerId);

    Object findAllByInternshipOfferId(Long offerId);

    Object findByInternshipOfferId(Long offerId);
}
