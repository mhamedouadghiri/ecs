package com.ecs.backend.controllers;

import com.ecs.backend.dto.OfferDto;
import com.ecs.backend.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer")
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOffer(@RequestBody OfferDto offerDto) {
        return offerService.saveOffer(offerDto);
    }
}
