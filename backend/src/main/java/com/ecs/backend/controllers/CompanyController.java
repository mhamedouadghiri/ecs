package com.ecs.backend.controllers;

import com.ecs.backend.services.CompanyService;
import com.ecs.backend.services.OfferService;
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
    private final OfferService offerService;

    @Autowired
    public CompanyController(CompanyService companyService, OfferService offerService) {
        this.companyService = companyService;
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/offers/{company-id}")
    public ResponseEntity<?>getAllOffers(@PathVariable("company-id") Long companyId){
        return offerService.getAllOffers(companyId);
    }
}
