package com.ecs.backend.repositories;

import com.ecs.backend.model.Application;
import com.ecs.backend.model.ApplicationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, ApplicationPK> {

    List<Application> findAllByInternshipOfferId(Long internshipOfferId);

    Application findByInternshipOfferIdAndStudentId(Long internshipOfferId, Long studentId);
}
