package endpoint

import (
	"github.com/gorilla/mux"
	"github.com/jackc/pgx/v4/pgxpool"
	"net/http"
)

type Endpoint struct {
	route   string
	handler func(w http.ResponseWriter, r *http.Request)
}

func CreateRouter(db *pgxpool.Pool) *mux.Router {
	router := mux.NewRouter().StrictSlash(true)

	endpoints := make([]Endpoint, 0)
	endpoints = append(endpoints, NewPersonService(db).GetEndpoints()...)
	endpoints = append(endpoints, NewAddressEndpoint()...)

	for _, e := range endpoints {
		router.HandleFunc(e.route, e.handler).Methods("GET", "POST", "PUT", "DELETE")
	}
	return router
}
