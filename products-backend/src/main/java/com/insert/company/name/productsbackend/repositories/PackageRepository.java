package com.insert.company.name.productsbackend.repositories;

import com.insert.company.name.productsbackend.models.entities.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Callum Cooper
 * TODO: For the purposes of this demo the repository is a
 * TODO: mutable in memory List. For a production grade system
 * TODO: a more permanent, robust data store would need to be adopted.
 */
public class PackageRepository {
    public static List<Package> dataStore = new ArrayList<>();

    public static List<Package> filterById(String id) {
        return dataStore
                .stream()
                .filter(p -> p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public static Boolean packageExistsById(String id) {
        return filterById(id).size() > 0;
    }

    public static Boolean updatePackage(Package _package) {
        return dataStore
                .stream()
                .filter(p -> !p.getId().equals(_package.getId()))
                .collect(Collectors.toList())
                .add(_package);
    }
}