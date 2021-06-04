package com.ecs.backend.controllers;

import com.ecs.backend.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/company")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    final private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return companyService.getAll();
    }

}
