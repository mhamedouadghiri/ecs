package com.ecs.backend.services;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.Company;
import com.ecs.backend.model.InternshipOffer;
import com.ecs.backend.repositories.CompanyRepository;
import com.ecs.backend.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    final private OffreRepository offreRepository;
    final private CompanyRepository companyRepository;

    @Autowired
    public OffreService(OffreRepository offreRepository, CompanyRepository companyRepository) {
        this.offreRepository = offreRepository;
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<?> saveOffer(UserDto user) {
        if (user.getTitle()==null || user.getCompanyId()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Long companyId;
        Integer duration = 0;
        Integer pay =0;
        try {
            companyId = Long.parseLong(user.getCompanyId());
            duration = Integer.parseInt(user.getDuration());
            pay = Integer.parseInt(user.getPay());

        }catch (NumberFormatException ignored){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Date startDate = null;
        try {
            startDate = new SimpleDateFormat("yyy-MM-dd").parse(user.getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = new SimpleDateFormat("yyy-MM-dd").parse(user.getEndDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Optional<Company> company = Optional.of(new Company());
        company = companyRepository.findById(companyId);
        InternshipOffer internshipOffer = new InternshipOffer(null,user.getTitle(),
                duration,
                startDate,
                endDate,
                user.getDescription(),
                pay,
                false,
                user.getField(),
                company.get());
        internshipOffer = offreRepository.save(internshipOffer);
        return ResponseEntity.ok(internshipOffer);
    }

    public ResponseEntity<?> getAllOffers(Long companyId) {
        List<InternshipOffer> internshipOffers = offreRepository.findAllByCompanyId(companyId);
        if (internshipOffers.isEmpty() || internshipOffers == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(internshipOffers);
    }
}
