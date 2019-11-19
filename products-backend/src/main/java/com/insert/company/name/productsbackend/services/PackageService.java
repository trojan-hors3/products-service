package com.insert.company.name.productsbackend.services;

import com.insert.company.name.productsbackend.clients.ProductClient;
import com.insert.company.name.productsbackend.models.entities.Package;
import com.insert.company.name.productsbackend.models.entities.Product;
import com.insert.company.name.productsbackend.models.http.CreatePackageRequest;
import com.insert.company.name.productsbackend.models.http.UpdatePackageRequest;
import com.insert.company.name.productsbackend.repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Callum Cooper
 */
@Service
public class PackageService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @FunctionalInterface
    interface ApplyNewPriceToProduct {
        Product apply(Product product, String baseCountry, String currency);
    }

    private ApplyNewPriceToProduct applyNewPriceToProduct = (originalProduct, baseCountry, currency) -> {
        final Double newPrice = exchangeRateService.exchange(originalProduct.getPrice(), baseCountry, currency);

        return new Product(originalProduct.getId(), originalProduct.getName(), newPrice.intValue());
    };

    public Package createPackage(CreatePackageRequest createPackageRequest, String baseCountry, String currency) {
        List<Product> products = createPackageRequest
                .getProductIds()
                .stream()
                .map(productClient::getProduct)
                .map(product -> applyNewPriceToProduct.apply(product, baseCountry, currency))
                .collect(Collectors.toList());

        Package newPackage = new Package(
                UUID.randomUUID().toString(),
                createPackageRequest.getName(),
                createPackageRequest.getDescription(),
                products);

        PackageRepository.dataStore.add(newPackage);

        return newPackage;
    }

    @FunctionalInterface
    interface ApplyUpdateToPackageStore {
        Package apply(Package originalPackage, Package newPackage);
    }

    private ApplyUpdateToPackageStore applyUpdateToPackageStore = (originalPackage, newPackage) -> {
        if (originalPackage.getId().equals(newPackage.getId()))
            return newPackage;
        else
            return originalPackage;
    };

    public Package updatePackage(String packageId, UpdatePackageRequest updatePackageRequest) {
        if(!PackageRepository.packageExistsById(packageId))
            throw new RuntimeException(String.format("Package %s could not be updated because it does not exist", packageId));
        else {
            Package newPackage = new Package(
                    packageId,
                    updatePackageRequest.getName(),
                    updatePackageRequest.getDescription(),
                    updatePackageRequest.getProducts()
            );

            PackageRepository.dataStore = PackageRepository
                    .dataStore
                    .stream()
                    .map(originalPackage -> applyUpdateToPackageStore.apply(originalPackage, newPackage))
                    .collect(Collectors.toList());

            return newPackage;
        }
    }

    public List<Package> deletePackage(String packageId) {
        if(!PackageRepository.packageExistsById(packageId))
            throw new RuntimeException(String.format("Package %s could not be deleted because it does not exist", packageId));
        else {
            PackageRepository.dataStore = PackageRepository
                    .dataStore
                    .stream()
                    .filter(p -> !p.getId().equals(packageId))
                    .collect(Collectors.toList());

            return PackageRepository.dataStore;
        }
    }
}
