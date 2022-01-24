package domain

import "github.com/jackc/pgtype"

type Person struct {
	PersonId  int64       `db:"personid"`
	BirthDate pgtype.Date `db:"birthdate"`
	Email     string      `db:"email"`
	Name      string      `db:"name"`
}
