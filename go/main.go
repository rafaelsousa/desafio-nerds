package main

import (
	"github.com/rafaelsousa/desafionerds/database"
	"github.com/rafaelsousa/desafionerds/endpoint"
	"log"
	"net/http"
	"time"
)

func main() {
	connection := database.Connect()
	defer connection.Close()
	router := endpoint.CreateRouter(connection)

	srv := &http.Server{
		Handler: router,
		Addr:    "127.0.0.1:8000",
		// Good practice: enforce timeouts for servers you create!
		WriteTimeout: 15 * time.Second,
		ReadTimeout:  15 * time.Second,
	}
	log.Print("Server started at the port 8000")
	log.Fatal(srv.ListenAndServe())
}
