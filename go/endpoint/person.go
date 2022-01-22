package endpoint

import (
	"encoding/json"
	"github.com/jmoiron/sqlx"
	"github.com/rafaelsousa/desafionerds/database/repository"
	"net/http"
)

type PersonService struct {
	databaseConnection *sqlx.DB
	endpoints          []Endpoint
}

func NewPersonService(db *sqlx.DB) PersonService {
	p := PersonService{
		databaseConnection: db,
		endpoints:          make([]Endpoint, 0),
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
	people, err := repository.ListAllPeople(p.databaseConnection)
	if err != nil {
		json.NewEncoder(writer).Encode(err)
	}
	json.NewEncoder(writer).Encode(people)
}
