# spring-postgresql-crud
CRUD using postgreSQL database | CRUD utilizando banco de dados postgreSQL

PREPARAÇÃO DO AMBIENTE LOCAL

https://confluence.atlassian.com/confbr1/configuracao-do-banco-de-dados-para-postgresql-933709553.html

TESTES

Buscar todas as pessoas cadastradas:
URI: http://localhost:8082/people
METHOD: GET

Buscar pessoa por id:
URI: http://localhost:8082/people/id/1
METHOD: GET

Incluir pessoa:
URI: http://localhost:8082/people
METHOD: POST
REQUEST:
'''json
{
    "id": 1,
    "name": "erison",
    "age": 28
}
'''

Alterar dados da pessoa:
URI: http://localhost:8082/people
METHOD: PUT
REQUEST:
'''json
{
    "id": 1,
    "name": "jonas",
    "age": 40
}
'''

Adicionar 1 ano na idade da pessoa:
URI: http://localhost:8082/people/id/1/add-year-old
METHOD: PATCH
