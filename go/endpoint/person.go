package endpoint

import (
	"encoding/json"
	"github.com/jackc/pgx/v4/pgxpool"
	"github.com/rafaelsousa/desafionerds/database/repository"
	"net/http"
)

type PersonService struct {
	databaseConnectionPool *pgxpool.Pool
	endpoints              []Endpoint
}

func NewPersonService(db *pgxpool.Pool) PersonService {
	p := PersonService{
		databaseConnectionPool: db,
		endpoints:              make([]Endpoint, 0),
	}
	endpoints := Endpoint{
		route:   "/listAllPeople",
		handler: p.listAll,
	}
	p.endpoints = append(p.endpoints, endpoints)

	return p

}

func (p PersonService) GetEndpoints() []Endpoint {
	return p.endpoints
}

func (p PersonService) listAll(writer http.ResponseWriter, request *http.Request) {
	people, err := repository.ListAllPeople(p.databaseConnectionPool)
	if err != nil {
		json.NewEncoder(writer).Encode(err)
	} else {
		json.NewEncoder(writer).Encode(people)
	}

}
