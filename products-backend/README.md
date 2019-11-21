# products-backend

##### System Requirements

![System Requirements](images/requirements.png)

##### Start the Tomcat server with Maven

```bash
mvn spring-boot:run
```

##### cURL scripts

###### Create a package

```bash
# default package
source ./scripts/cURL/create_package.sh && createDefaultPackage

# foreign package
source ./scripts/cURL/create_package.sh && createForeignPackage
```

###### Retrieve a package

```bash
source ./scripts/cURL/retrieve_package.sh && retrievePackage
```

###### Update a package

```bash
source ./scripts/cURL/update_package.sh && updatePackage
```

###### Delete a package

```bash
source ./scripts/cURL/delete_package.sh && deletePackage
```

###### List all packages

```bash
source ./scripts/cURL/list_packages.sh && listPackages
```

##### Run unit tests

```bash
cd products-backend && mvn clean test
```

