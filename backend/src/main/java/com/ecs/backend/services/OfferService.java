package com.ecs.backend.services;

import com.ecs.backend.dto.OfferDto;
import com.ecs.backend.model.Company;
import com.ecs.backend.model.InternshipOffer;
import com.ecs.backend.repositories.CompanyRepository;
import com.ecs.backend.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository, CompanyRepository companyRepository) {
        this.offerRepository = offerRepository;
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<?> saveOffer(OfferDto offerDto) {
        String title = offerDto.getTitle();
        Long companyId = offerDto.getCompanyId();
        if (title == null || companyId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Company company = companyRepository.findById(companyId).orElse(null);
        if (company == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        InternshipOffer savedEntity = offerRepository.save(
                new InternshipOffer(null,
                        title,
                        offerDto.getDuration(),
                        offerDto.getStartDate(),
                        offerDto.getEndDate(),
                        offerDto.getDescription(),
                        offerDto.getPay(),
                        false,
                        offerDto.getField(),
                        company));

        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<?> getAllOffers(Long companyId) {
        List<InternshipOffer> internshipOffers = offerRepository.findAllByCompanyId(companyId);
        if (internshipOffers == null || internshipOffers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(internshipOffers);
    }
}
