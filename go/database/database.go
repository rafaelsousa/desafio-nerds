package database

import (
	"github.com/jmoiron/sqlx"
)

func Connect() *sqlx.DB {
	db := sqlx.MustConnect("pq", "user=desafio dbname=desafio password=desafio postgresql://localhost:5432/desafio")
	return db
}
