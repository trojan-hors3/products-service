package com.insert.company.name.productsbackend.fixtures;

import com.insert.company.name.productsbackend.models.entities.Package;

import java.util.Collections;

/**
 * @author Callum Cooper
 */
public class PackageFixture extends ProductFixture {

    public final String UNKNOWN_PACKAGE_ID = "UNKNOWN_PACKAGE_ID";

    public Package singlePackage() throws Exception {
        return new Package(
                "756ed816-043b-480d-b7e1-0473b47714d4",
                "My first package",
                "A newly created package",
                Collections.singletonList(singleProduct()));
    }
}
