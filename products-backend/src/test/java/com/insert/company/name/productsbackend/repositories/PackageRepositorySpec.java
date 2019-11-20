package com.insert.company.name.productsbackend.repositories;

import com.insert.company.name.productsbackend.fixtures.PackageFixture;
import com.insert.company.name.productsbackend.models.entities.Package;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Callum Cooper
 */
public class PackageRepositorySpec extends PackageFixture {

    private void populateDataStore(int size) throws Exception {
        for (int i = 1; i <= size; i++) {
            PackageRepository.dataStore.add(singlePackage());
        }
    }

    @Before
    public void setup() {
        PackageRepository.dataStore.clear();
    }

    @Test
    public void filterByIdSpec() throws Exception {
        System.out.println("* filterById() should return the Package for a given package ID if one exists");

        populateDataStore(1);

        final List<Package> actualPackage = PackageRepository.filterById("756ed816-043b-480d-b7e1-0473b47714d4");

        assertThat(actualPackage.size())
                .isEqualTo(1);
    }

    @Test
    public void filterByIdSpec01() {
        System.out.println("* filterById() should return an empty collection when a given package ID does not exist");

        final List<Package> actualPackage = PackageRepository.filterById(UNKNOWN_PACKAGE_ID);

        assertThat(actualPackage.size())
                .isEqualTo(0);
    }

    @Test
    public void packageExistsByIdSpec() throws Exception {
        System.out.println("* packageExistsById() should return true if a given package ID exists");

        populateDataStore(1);

        final Boolean actualPackageExistsById = PackageRepository.packageExistsById("756ed816-043b-480d-b7e1-0473b47714d4");

        assertThat(actualPackageExistsById).isEqualTo(true);
    }

    @Test
    public void packageExistsByIdSpec01() {
        System.out.println("* packageExistsById() should return false if a given package ID does not exist");

        final Boolean actualPackageExistsById = PackageRepository.packageExistsById("756ed816-043b-480d-b7e1-0473b47714d4");

        assertThat(actualPackageExistsById).isEqualTo(false);
    }
}