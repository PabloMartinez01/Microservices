package com.pablodev.organizationservice.organization.infrastructure.controller.mapper;

import com.pablodev.organizationservice.organization.application.create.CreateOrganizationCommand;
import com.pablodev.organizationservice.organization.application.update.UpdateOrganizationCommand;
import com.pablodev.organizationservice.organization.infrastructure.controller.dto.create.CreateOrganizationRequest;
import com.pablodev.organizationservice.organization.infrastructure.controller.dto.update.UpdateOrganizationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationRequestMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    CreateOrganizationCommand toCreateOrganizationCommand(CreateOrganizationRequest request);
    
    @Mapping(target = "id", source = "id")
    UpdateOrganizationCommand toUpdateOrganizationCommand(String id, UpdateOrganizationRequest request);
}
