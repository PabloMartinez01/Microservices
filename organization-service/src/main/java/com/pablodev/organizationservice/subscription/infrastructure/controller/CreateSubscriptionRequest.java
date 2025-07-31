package com.pablodev.organizationservice.subscription.infrastructure.controller;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateSubscriptionRequest(
        @NotBlank(message = "Organization id can not be empty")
        String organizationId,

        @NotNull(message = "Start date can not be null")
        @Future(message = "Start date must be future")
        LocalDate startDate,

        @NotNull(message = "Expiration date can not be null")
        @Future(message = "Expiration date must be future")
        LocalDate expirationDate
) {

}
