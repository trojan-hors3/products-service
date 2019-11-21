#!/usr/bin/env bash

# Package ID query param is a random UUID amend ID accordingly
function retrievePackage() {
    curl -X GET \
  http://localhost:8080/package/1dad25d4-721b-4cc8-99e8-24a6df6de8fc \
  -H 'Host: localhost:8080' \ | jq .
}