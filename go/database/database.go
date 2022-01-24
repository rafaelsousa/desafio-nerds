package database

import (
	"context"
	"fmt"
	"github.com/jackc/pgx/v4/pgxpool"
	"os"
)

func Connect() *pgxpool.Pool {
	conn, err := pgxpool.Connect(context.Background(), "postgresql://desafio:desafio@localhost:5432/desafio")
	if err != nil {
		fmt.Fprintf(os.Stderr, "Unable to connect to database: %v\n", err)
		os.Exit(1)
	}
	return conn
}
