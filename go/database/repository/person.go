package repository

import (
	"context"
	"github.com/georgysavva/scany/pgxscan"
	"github.com/jackc/pgx/v4/pgxpool"
	"github.com/rafaelsousa/desafionerds/database/domain"
)

func ListAllPeople(pool *pgxpool.Pool) ([]domain.Person, error) {

	people := make([]domain.Person, 0)
	err := pgxscan.Select(context.Background(), pool, &people, "select * from Person")
	if err != nil {
		return nil, err
	}

	return people, nil
}
