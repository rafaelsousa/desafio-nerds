package main

import (
	"github.com/rafaelsousa/desafionerds/database"
	"github.com/rafaelsousa/desafionerds/endpoint"
)

func main() {

	db := database.Connect()
	endpoint.RegisterEndpoints(db)
}
