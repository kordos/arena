Endpoints:

* Save tournament 
POST `/tournaments`
Body:
```json
{
	"points": 11,
	"capacity": 22
}
```
* Get tournament 
GET `/tournaments/{id}`

* Add creature to tournament 
POST `/tournaments/{id}/creatures`
Body:
```json
{
	"lifePoints": 10,
	"strength": 20,
	"dexterity": 10,
	"initiative": 5,
	"velocity": 3,
	"endurance": 4,
	"numberOfAttacks": 1,
	"numberOfDodges": 1,
	"type": "ORC"
}
```

Database:
Application configuration inside: `resources/application.properties`

Crete commands:
```mysql
create database arena;
create user 'arena'@'localhost' identified by 'arena';
grant all on arena.* to 'arena'@'localhost';
```

mysql on vagrant:
`docker run -d -p 3306:3306 --env="MYSQL_ROOT_PASSWORD=root" --name=test-mysql mysql`

user: `root`
pass: `root`

