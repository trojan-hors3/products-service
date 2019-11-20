package com.insert.company.name.productsbackend.services;

import com.insert.company.name.productsbackend.clients.ProductClient;
import com.insert.company.name.productsbackend.fixtures.ExchangeRateFixture;
import com.insert.company.name.productsbackend.fixtures.PackageFixture;
import com.insert.company.name.productsbackend.fixtures.ProductFixture;
import com.insert.company.name.productsbackend.models.entities.Package;
import com.insert.company.name.productsbackend.models.entities.Product;
import com.insert.company.name.productsbackend.models.http.CreatePackageRequest;
import com.insert.company.name.productsbackend.models.http.UpdatePackageRequest;
import com.insert.company.name.productsbackend.repositories.PackageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Callum Cooper
 */
@RunWith(MockitoJUnitRunner.class)
public class PackageServiceSpec {

    private PackageFixture packageFixture = new PackageFixture();
    private ProductFixture productFixture = new ProductFixture();
    private ExchangeRateFixture exchangeRateFixture = new ExchangeRateFixture();

    private String packageId;

    @Mock
    ProductClient productClient;

    @Mock
    ExchangeRateService exchangeRateService;

    @InjectMocks
    PackageService packageService;

    private void populateDateStore(int size) {
        // Iterate the input size and create a new package for each iteration.
        IntStream.rangeClosed(1, size)
                .forEach(i -> packageService.createPackage(
                                new CreatePackageRequest("Package " + i, "A newly created package " + i, Collections.singletonList("VqKb4tyj9V6i")),
                                exchangeRateFixture.UNITED_STATES_DOLLAR_ABBREVIATION,
                                exchangeRateFixture.UNITED_STATES_DOLLAR_ABBREVIATION));

        // Ensure the packages have been successfully created
        assertThat(PackageRepository.dataStore.size())
                .isEqualTo(size);
    }

    @Before
    public void setup() throws Exception {
        PackageRepository.dataStore.clear();

        Mockito.when(productClient.getProduct(Mockito.eq("VqKb4tyj9V6i")))
                .thenReturn(productFixture.singleProduct());

        Mockito.when(exchangeRateService.exchange(
                Mockito.eq(1149),
                Mockito.eq(exchangeRateFixture.UNITED_STATES_DOLLAR_ABBREVIATION),
                Mockito.eq(exchangeRateFixture.UNITED_STATES_DOLLAR_ABBREVIATION)))
                .thenReturn(1149.00);
    }

    @Test
    public void createPackageSpec() {
        System.out.println("* createPackage() Persists a new package to a data store with relative priced products and return package");

        assertThat(PackageRepository.dataStore.size())
                .isEqualTo(0);

        final Package actualCreatedPackage = packageService.createPackage(
                new CreatePackageRequest("My first package", "A newly created package", Collections.singletonList("VqKb4tyj9V6i")),
                exchangeRateFixture.UNITED_STATES_DOLLAR_ABBREVIATION,
                exchangeRateFixture.UNITED_STATES_DOLLAR_ABBREVIATION);

        final Package expectedCreatedPackage = new Package(
                UUID.randomUUID().toString(),
                "My first package",
                "A newly created package",
                Collections.singletonList(new Product(
                        "VqKb4tyj9V6i",
                        "Shield",
                        1149
                ))
        );

        assertThat(actualCreatedPackage.getProducts().size())
                .isEqualTo(1);

        assertThat(PackageRepository.dataStore.size())
                .isEqualTo(1);

        assertThat(actualCreatedPackage.getName())
                .isEqualTo(expectedCreatedPackage.getName());

        assertThat(actualCreatedPackage.getDescription())
                .isEqualTo(expectedCreatedPackage.getDescription());

        assertThat(actualCreatedPackage.getProducts().get(0).getId())
                .isEqualTo(expectedCreatedPackage.getProducts().get(0).getId());

        assertThat(actualCreatedPackage.getProducts().get(0).getName())
                .isEqualTo(expectedCreatedPackage.getProducts().get(0).getName());
    }

    @Test
    public void updatePackageSpec() throws Exception {
        System.out.println("* updatePackage() updates a package by a package ID in a data store if one exists and return updated package");

        // Create a package in the data store to update
        populateDateStore(1);

        // Set the packageId to the head of the package store (i.e the newly created package)
        this.packageId = PackageRepository.dataStore.get(0).getId();

        final Package actualUpdatedPackage = packageService.updatePackage(
                packageId,
                new UpdatePackageRequest(
                        "My first package UPDATED",
                        "A newly created package that has been UPDATED",
                        Arrays.asList(productFixture.singleProduct(), productFixture.singleProduct())));

        final Package expectedUpdatedPackage = new Package(
                packageId,
                "My first package UPDATED",
                "A newly created package that has been UPDATED",
                Arrays.asList(productFixture.singleProduct(), productFixture.singleProduct()));

        // The Package data store size after update is still 1
        assertThat(PackageRepository.dataStore.size())
                .isEqualTo(1);

        // The size of the products in the head of the package store is now 2 after update
        assertThat(PackageRepository.dataStore.get(0).getProducts().size())
                .isEqualTo(2);

        // The package name has been updated
        assertThat(actualUpdatedPackage.getName())
                .isEqualTo(expectedUpdatedPackage.getName());

        // The package description has been updated
        assertThat(actualUpdatedPackage.getDescription())
                .isEqualTo(expectedUpdatedPackage.getDescription());

        // The package products has have been updated
        assertThat(actualUpdatedPackage.getProducts().size())
                .isEqualTo(expectedUpdatedPackage.getProducts().size());
    }

    @Test(expected = RuntimeException.class)
    public void updatePackageSpec01() throws Exception {
        System.out.println("* updatePackage() should throw a runtime exception if an update package is issued upon an non-existent package ID");

        packageService.updatePackage(
                packageFixture.UNKNOWN_PACKAGE_ID,
                new UpdatePackageRequest(
                        "My first package UPDATED",
                        "A newly created package that has been UPDATED",
                        Arrays.asList(productFixture.singleProduct(), productFixture.singleProduct())));
    }

    @Test
    public void deletePackageSpec() {
        System.out.println("* deletePackage() deletes a package by a package ID if one exists and returns the updated data store");

        // Create a package in the data store to delete
        populateDateStore(2);

        // Set the packageId to the head of the package store (i.e the newly created package)
        this.packageId = PackageRepository.dataStore.get(0).getId();

        final List<Package> actualDeletePackage = packageService.deletePackage(packageId);

        assertThat(PackageRepository.dataStore.size())
                .isEqualTo(1);

        assertThat(actualDeletePackage.size())
                .isEqualTo(1);
    }

    @Test(expected = RuntimeException.class)
    public void deletePackageSpec01() {
        System.out.println("* deletePackage() should throw a runtime exception if a delete package is issued upon a non-existent package ID");

        packageService.deletePackage(packageFixture.UNKNOWN_PACKAGE_ID);
    }
}
