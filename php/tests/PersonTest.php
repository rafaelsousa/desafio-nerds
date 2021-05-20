<?php

use Laravel\Lumen\Testing\DatabaseMigrations;
use Laravel\Lumen\Testing\DatabaseTransactions;

class PersonTest extends TestCase
{
    use DatabaseMigrations;

    public function testInsert()
    {
        $person = \App\Models\Person::factory()->makeOne()->toArray();

        $this
            ->post('/person', $person)
            ->assertResponseOk();

        $this->seeInDatabase('persons', ['email' => $person['email']]);
    }

    public function testUpdate()
    {
        $person = \App\Models\Person::factory()->create();

        $newEmail = 'aaaa@aaa.com.br';
        $this->put("/person/$person->id",['email' => $newEmail])
            ->assertResponseOk();

        $this->seeInDatabase('persons', ['email' => $newEmail]);
    }

    public function testDelete()
    {
        $person = \App\Models\Person::factory()->create();

        $this->delete("/person/$person->id")
            ->assertResponseOk();

        $this->notSeeInDatabase('persons', ['id' => $person->id]);
    }
}
