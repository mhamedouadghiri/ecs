package com.ecs.backend.services;

import com.ecs.backend.model.Company;
import com.ecs.backend.repositories.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    final private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public ResponseEntity<?> getAll() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty() || companies == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(companies);
    }
}
