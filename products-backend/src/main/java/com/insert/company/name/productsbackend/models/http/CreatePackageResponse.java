package com.insert.company.name.productsbackend.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insert.company.name.productsbackend.models.entities.Package;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Callum Cooper
 */
@AllArgsConstructor
@Getter
public class CreatePackageResponse {

    @JsonProperty
    int statusCode;

    @JsonProperty
    String description;

    @JsonProperty
    Package createdPackage;
}
