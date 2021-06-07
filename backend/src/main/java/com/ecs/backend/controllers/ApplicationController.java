package com.ecs.backend.controllers;

import com.ecs.backend.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/{offer-id}")
    public ResponseEntity<?> getApplications(@PathVariable("offer-id") Long offerId) {
        return applicationService.getApplications(offerId);
    }

    @GetMapping("/{offer-id}/{student-id}")
    public ResponseEntity<?> getApplicationAndStudentInfoRegardingOffer(@PathVariable("offer-id") Long offerId,
                                                                        @PathVariable("student-id") Long studentId) {
        return applicationService.getApplicationAndStudentInfoRegardingOffer(offerId, studentId);
    }
}
