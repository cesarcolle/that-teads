#!/usr/bin/env bash

echo "add bids..."
curl -H "Content-Type: application/json" -X POST -d '{"name":"xyz", "numbers" : [1, 2] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"abc", "numbers" : [3, 4, 5] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"yycc", "numbers" : [1, 2] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"efg", "numbers" : [8, 9] }' http://localhost:8080/addBids
curl -H "Content-Type: application/json" -X POST -d '{"name":"yycc", "numbers" : [7, 10] }' http://localhost:8080/addBids

echo "launch lottery"
curl -H "Content-Type: application/json" -X GET  http://localhost:8080/lottery?reservedPrice=8
