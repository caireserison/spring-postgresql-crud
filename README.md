# spring-postgresql-crud
CRUD using postgreSQL database | CRUD utilizando banco de dados postgreSQL

PREPARAÇÃO DO AMBIENTE LOCAL

https://confluence.atlassian.com/confbr1/configuracao-do-banco-de-dados-para-postgresql-933709553.html

TESTES

Buscar todas as pessoas cadastradas: <br/>
URI: http://localhost:8082/people <br/>
METHOD: GET

Buscar pessoa por id: <br/>
URI: http://localhost:8082/people/id/1 <br/>
METHOD: GET

Incluir pessoa: <br/>
URI: http://localhost:8082/people <br/>
METHOD: POST <br/>
REQUEST: <br/>
```json
{
    "id": 1,
    "name": "erison",
    "age": 28
}
```

Alterar dados da pessoa: <br/>
URI: http://localhost:8082/people <br/>
METHOD: PUT <br/>
REQUEST: <br/>
```json
{
    "id": 1,
    "name": "jonas",
    "age": 40
}
```

Adicionar 1 ano na idade da pessoa: <br/>
URI: http://localhost:8082/people/id/1/add-year-old <br/>
METHOD: PATCH
