#!/usr/bin/env bash

function listPackages() {
    curl -X GET \
  http://localhost:8080/packages \
  -H 'Host: localhost:8080' | jq .
}