package com.pablodev.organizationservice.subscription.infrastructure.controller;

import jakarta.validation.constraints.NotBlank;

public record CreateSubscriptionRequest(
        @NotBlank(message = "Organization id can not be empty")
        String organizationId
) {

}
