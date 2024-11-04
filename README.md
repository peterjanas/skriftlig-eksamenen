dev http outputs

populate:HTTP
POST http://localhost:7007/api/trips/populate

HTTP/1.1 201 Created
Date: Mon, 04 Nov 2024 11:47:01 GMT
Content-Type: text/plain
Content-Length: 31

Database populated successfully

Response code: 201 (Created); Time: 152ms (152 ms); Content length: 31 bytes (31 B)

getall:
GET http://localhost:7007/api/trips/

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:47:29 GMT
Content-Type: application/json
Content-Encoding: gzip
Content-Length: 376

[
{
"id": 1,
"startTime": [
2024,
8,
20,
10,
0
],
"endTime": [
2024,
8,
20,
16,
0
],
"startLocation": "Historic Village",
"name": "Historic Tour",
"price": 180,
"category": "HISTORICAL",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
},
{
"id": 2,
"startTime": [
2024,
6,
1,
8,
0
],
"endTime": [
2024,
6,
1,
18,
0
],
"startLocation": "Mountain Base",
"name": "Mountain Excursion",
"price": 150,
"category": "ADVENTURE",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
},
{
"id": 3,
"startTime": [
2024,
7,
15,
9,
0
],
"endTime": [
2024,
7,
15,
17,
0
],
"startLocation": "Forest Trail",
"name": "Forest Exploration",
"price": 200,
"category": "NATURE",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
},
{
"id": 4,
"startTime": [
2024,
6,
1,
8,
0
],
"endTime": [
2024,
6,
1,
18,
0
],
"startLocation": "Mountain Base",
"name": "Mountain Excursion",
"price": 150,
"category": "ADVENTURE",
"guide": {
"id": 2,
"firstName": "Bob",
"lastName": "Thompson",
"email": "bob.thompson@example.com",
"phone": 987654321,
"yearsOfExperience": 8
}
},
{
"id": 5,
"startTime": [
2024,
8,
20,
10,
0
],
"endTime": [
2024,
8,
20,
16,
0
],
"startLocation": "Historic Village",
"name": "Historic Tour",
"price": 180,
"category": "HISTORICAL",
"guide": {
"id": 2,
"firstName": "Bob",
"lastName": "Thompson",
"email": "bob.thompson@example.com",
"phone": 987654321,
"yearsOfExperience": 8
}
},
{
"id": 6,
"startTime": [
2024,
7,
15,
9,
0
],
"endTime": [
2024,
7,
15,
17,
0
],
"startLocation": "Forest Trail",
"name": "Forest Exploration",
"price": 200,
"category": "NATURE",
"guide": {
"id": 2,
"firstName": "Bob",
"lastName": "Thompson",
"email": "bob.thompson@example.com",
"phone": 987654321,
"yearsOfExperience": 8
}
}
]

getbyid:
GET http://localhost:7007/api/trips/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:48:00 GMT
Content-Type: application/json
Content-Length: 291

{
"id": 1,
"startTime": [
2024,
8,
20,
10,
0
],
"endTime": [
2024,
8,
20,
16,
0
],
"startLocation": "Historic Village",
"name": "Historic Tour",
"price": 180,
"category": "HISTORICAL",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
}


getTripsByGuideId:
GET http://localhost:7007/api/trips/guides/1

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 12:14:00 GMT
Content-Type: application/json
Content-Length: 871

[
{
"id": 2,
"startTime": [
2024,
6,
1,
8,
0
],
"endTime": [
2024,
6,
1,
18,
0
],
"startLocation": "Mountain Base",
"name": "Mountain Excursion",
"price": 150,
"category": "ADVENTURE",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
},
{
"id": 1,
"startTime": [
2024,
8,
20,
10,
0
],
"endTime": [
2024,
8,
20,
16,
0
],
"startLocation": "Historic Village",
"name": "Historic Tour",
"price": 180,
"category": "HISTORICAL",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
},
{
"id": 3,
"startTime": [
2024,
7,
15,
9,
0
],
"endTime": [
2024,
7,
15,
17,
0
],
"startLocation": "Forest Trail",
"name": "Forest Exploration",
"price": 200,
"category": "NATURE",
"guide": {
"id": 1,
"firstName": "Alice",
"lastName": "Walker",
"email": "alice.walker@example.com",
"phone": 123456789,
"yearsOfExperience": 10
}
}


create:
POST http://localhost:7007/api/trips/

HTTP/1.1 201 Created
Date: Mon, 04 Nov 2024 11:48:19 GMT
Content-Type: application/json
Content-Length: 169

{
"id": 7,
"startTime": [
2024,
6,
1,
8,
0
],
"endTime": [
2024,
6,
1,
18,
0
],
"startLocation": "Mountain Base",
"name": "Mountain Excursion",
"price": 150,
"category": "ADVENTURE",
"guide": null
}

add guide to trip:

PUT http://localhost:7007/api/trips/7/guides/2

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:45:07 GMT
Content-Type: text/plain
Content-Length: 19

Guide added to trip


delete:
DELETE http://localhost:7007/api/trips/1

HTTP/1.1 204 No Content
Date: Mon, 04 Nov 2024 11:45:40 GMT
Content-Type: text/plain

<Response body is empty>Response code: 204 (No Content); Time: 34ms (34 ms); Content length: 0 bytes (0 B)


Theoretical question: Why do we suggest a PUT method for adding a guide to a trip instead of a POST
method?
vi bruger put da vi opdaterer en eksisterende ressource, og post bruges til at oprette en ny ressource
at vi bruger put betyder også at vores update request er idempotency.
hvilket betyder at ligegyldigt hvor mange gange vi sender requesten vil resultatet være det samme.