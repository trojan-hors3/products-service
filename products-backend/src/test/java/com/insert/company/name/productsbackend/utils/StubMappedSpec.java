package com.insert.company.name.productsbackend.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class StubMappedSpec {
    protected static final ObjectMapper objectMapper = new ObjectMapper();

    protected<T> T loadStubMapping(String location, Class<T> clazz) throws Exception {
        return objectMapper.readValue(this.getClass().getResourceAsStream(location), clazz);
    }

    protected<T> T loadStubMappings(String location, TypeReference<T> typeReference) throws Exception {
        return objectMapper.readValue(this.getClass().getResourceAsStream(location), typeReference);
    }
}