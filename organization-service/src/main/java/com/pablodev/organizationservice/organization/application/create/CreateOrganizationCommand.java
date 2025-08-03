package com.pablodev.organizationservice.organization.application.create;

import com.pablodev.organizationservice.organization.application.OrganizationAddressData;
import com.pablodev.shared.domain.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class CreateOrganizationCommand implements Command {

    private final String id;
    private final String name;
    private final String type;
    private final OrganizationAddressData address;

}