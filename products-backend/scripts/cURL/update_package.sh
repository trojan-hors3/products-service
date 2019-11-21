#!/usr/bin/env bash

# Package ID query param is a random UUID amend ID accordingly
function updatePackage() {
    curl -X PUT \
      http://localhost:8080/package/1dad25d4-721b-4cc8-99e8-24a6df6de8fc \
      -H 'Content-Type: application/json' \
      -H 'Host: localhost:8080' \
      -d '{
        "name": "My first default package updated",
        "description": "A newly created default package that has been updated",
        "products": [
                {
                    "id": "VqKb4tyj9V6i",
                    "name": "Shield updated",
                    "price": 2149
                }
            ]
    }' | jq .
}