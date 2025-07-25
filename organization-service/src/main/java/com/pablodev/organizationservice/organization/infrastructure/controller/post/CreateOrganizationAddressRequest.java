package com.pablodev.organizationservice.organization.infrastructure.controller.post;

import jakarta.validation.constraints.NotBlank;

public record CreateOrganizationAddressRequest(
        @NotBlank(message = "Street can not be blank") String street,
        @NotBlank(message = "City can not be blank") String city,
        @NotBlank(message = "State can not be blank") String state,
        @NotBlank(message = "Country can not be blank") String country
) {

}
