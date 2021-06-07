package com.ecs.backend.controllers;

import com.ecs.backend.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    final private ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @GetMapping("/application/{offer-id}")
    public ResponseEntity<?> getApplications(@PathVariable("offer-id") Long offerId){
        return applicationService.getApplications(offerId);
    }

}
