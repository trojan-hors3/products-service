#!/usr/bin/env bash

function createDefaultPackage() {
    curl -X POST \
  http://localhost:8080/package/create \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
	"name": "My first default package",
	"description": "A newly created default package",
	"productIds" : [ "VqKb4tyj9V6i", "DXSQpv6XVeJm"]
}' | jq .
}

function createForeignPackage() {
    curl -X POST \
  'http://localhost:8080/package/create?base=USD&currency=GBP' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8080' \
  -d '{
	"name": "My first foreign package",
	"description": "A newly created foreign package",
	"productIds" : [ "VqKb4tyj9V6i", "DXSQpv6XVeJm"]
}' | jq .
}