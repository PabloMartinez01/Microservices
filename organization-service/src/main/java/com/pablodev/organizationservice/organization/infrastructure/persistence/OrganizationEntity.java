package com.pablodev.organizationservice.organization.infrastructure.persistence;

import com.pablodev.organizationservice.organization.domain.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "organizations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationEntity {
    @Id
    private String id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String country;
    private String type;
}
