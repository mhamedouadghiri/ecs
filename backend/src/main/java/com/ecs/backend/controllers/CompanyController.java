package com.ecs.backend.controllers;

import com.ecs.backend.services.CompanyService;
import com.ecs.backend.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {

    private final CompanyService companyService;
    private final OffreService offreService;

    @Autowired
    public CompanyController(CompanyService companyService, OffreService offreService) {
        this.companyService = companyService;
        this.offreService = offreService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/offers/{company-id}")
    public ResponseEntity<?>getAllOffers(@PathVariable("company-id") Long companyId){
        return offreService.getAllOffers(companyId);
    }
}
