package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.application.create.CreateOrganizationRequest;
import com.pablodev.organizationservice.organization.application.create.OrganizationCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationPostController {

    private final OrganizationCreator creator;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOrganizationHttpRequest organizationHttpRequest) {


    }

    record CreateOrganizationHttpRequest(
            String name,
            String type,
            CreateOrganizationAddressHttpRequest address
    ){
        record CreateOrganizationAddressHttpRequest(
                String street,
                String city,
                String state,
                String country
        ){}
    }

}



