package com.pablodev.organizationservice.organization.infrastructure.controller.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrganizationHttpRequest(
        @NotBlank(message = "Name can not be blank") String name,
        @NotBlank(message = "Type can not be blank") String type,
        @NotNull(message = "Address can not be null") CreateOrganizationAddressHttpRequest address
){
    record CreateOrganizationAddressHttpRequest(
            @NotBlank(message = "Street can not be blank") String street,
            @NotBlank(message = "City can not be blank") String city,
            @NotBlank(message = "State can not be blank") String state,
            @NotBlank(message = "Country can not be blank") String country
    ){}
}
