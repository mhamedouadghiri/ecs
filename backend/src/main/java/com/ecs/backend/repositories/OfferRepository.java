package com.ecs.backend.repositories;

import com.ecs.backend.model.InternshipOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<InternshipOffer, Long> {

    List<InternshipOffer> findAllByCompanyId(Long companyId);
}
