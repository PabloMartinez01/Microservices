package com.pablodev.organizationservice.organization.infrastructure.controller.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateOrganizationRequest(
        @NotBlank(message = "Name can not be blank") String name,
        @NotBlank(message = "Type can not be blank") String type,
        @NotNull(message = "Address can not be null") UpdateOrganizationAddressRequest address
) {

}
