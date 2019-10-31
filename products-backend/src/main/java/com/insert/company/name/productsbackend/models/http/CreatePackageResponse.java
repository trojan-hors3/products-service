package com.insert.company.name.productsbackend.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Callum Cooper
 */
@AllArgsConstructor
@Getter
public class CreatePackageResponse<T> {

    @JsonProperty
    int statusCode;

    @JsonProperty
    String description;

    @JsonProperty
    T obj;
}
