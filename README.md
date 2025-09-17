# WebFlux Postgres


```bash
# Crear
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"Raul","email":"raul@test.com"}'

# Listar
curl http://localhost:8080/users

# Buscar por ID
curl http://localhost:8080/users/1

# Actualizar
curl -X PUT http://localhost:8080/users/1 -H "Content-Type: application/json" -d '{"name":"Raul Updated","email":"raul@demo.com"}'

# Eliminar
curl -X DELETE http://localhost:8080/users/1

# Paginación
curl "http://localhost:8080/users/paged?page=0&size=2"

# Buscar por email
curl "http://localhost:8080/users/search/email?email=raul@test.com"

# Buscar por nombre (like)
curl "http://localhost:8080/users/search/name?name=raul"


```