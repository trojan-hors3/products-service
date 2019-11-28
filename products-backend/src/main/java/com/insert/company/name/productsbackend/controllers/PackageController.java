package com.insert.company.name.productsbackend.controllers;

import com.insert.company.name.productsbackend.models.http.CreatePackageRequest;
import com.insert.company.name.productsbackend.models.entities.Package;
import com.insert.company.name.productsbackend.models.http.CreatePackageResponse;
import com.insert.company.name.productsbackend.models.http.UpdatePackageRequest;
import com.insert.company.name.productsbackend.repositories.PackageRepository;
import com.insert.company.name.productsbackend.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Callum Cooper
 */
@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;

    private final String DEFAULT_BASE = "USD";
    private final String DEFAULT_CURRENCY = "USD";

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/package/create", method = RequestMethod.POST)
    public ResponseEntity<Package> createPackage(
            @RequestBody CreatePackageRequest createPackageRequest,
            @RequestParam(value = "base", required = false, defaultValue = DEFAULT_BASE) String baseCountry,
            @RequestParam(value = "currency", required = false, defaultValue = DEFAULT_CURRENCY) String currency,
            UriComponentsBuilder uriComponentsBuilder) {
        final Package createdPackage = packageService.createPackage(createPackageRequest, baseCountry, currency);

        final URI createdPackageLocation = uriComponentsBuilder
                .path("/package/{id}")
                .buildAndExpand(createdPackage.getId())
                .toUri();

        return ResponseEntity
                .created(createdPackageLocation)
                .body(createdPackage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/package/{id}", method = RequestMethod.GET)
    public ResponseEntity<Package> getPackage(@PathVariable String id) {
        List<Package> filteredPackages = PackageRepository.filterById(id);
        Package headPackage = filteredPackages.subList(0, 1).get(0);

        return ResponseEntity
                .ok()
                .header("Content-type", "application/json")
                .body(headPackage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/package/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Package> updatePackage(@PathVariable String id,
                                                 @RequestBody UpdatePackageRequest updatePackageRequest) {
        final Package updatedPackage = packageService.updatePackage(id, updatePackageRequest);

        return ResponseEntity
                .ok()
                .header("Content-type", "application/json")
                .body(updatedPackage);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/package/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<List<Package>> deletePackage(@PathVariable String id) {
        final List<Package> deletedPackageStore = packageService.deletePackage(id);

        return ResponseEntity
                .ok()
                .header("Content-type", "application/json")
                .body(deletedPackageStore);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/packages", method = RequestMethod.GET)
    public ResponseEntity<List<Package>> getPackages() {
        return ResponseEntity
                .ok()
                .header("Content-Type", "application/json")
                .body(PackageRepository.dataStore);
    }
}