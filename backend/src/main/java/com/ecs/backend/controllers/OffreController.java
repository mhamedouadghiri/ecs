package com.ecs.backend.controllers;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer")
public class OffreController {

    final private OffreService offreService;

    @Autowired
    public OffreController(OffreService offreService) {
        this.offreService = offreService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOffer(@RequestBody UserDto user){
        return offreService.saveOffer(user);
    }
}
