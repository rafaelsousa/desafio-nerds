package repository

import (
	"github.com/jmoiron/sqlx"
	"github.com/rafaelsousa/desafionerds/database/domain"
)

func ListAllPeople(db *sqlx.DB) ([]domain.Person, error) {

	var people []domain.Person
	err := db.Select(&people, "")
	if err != nil {
		return nil, err
	}
	return people, nil
}
