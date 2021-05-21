from locust import HttpUser, task, between
import faker
import random as r

faker = faker.Faker()

class FlowException(Exception):
    pass

class MyUser(HttpUser):
    wait_time = between(5, 15)
    created_persons = []

    @task(3)
    def create(self):
        new_person = {'name': faker.name(), 'email': faker.email(), 'birthday': faker.date() }
        person_response = self.client.post('/person', json=new_person, name="create person")
        if person_response.status_code != 200:
            raise FlowException(person_response.json())
        person_id = person_response.json().get('id')
        self.created_persons.append(person_id)

    @task(2)
    def update(self):
        if len(self.created_persons) == 0:
            return
        person_id = r.choice(self.created_persons)
        new_person = { "name": faker.name() }
        person_response = self.client.put(f'/person/{person_id}', json=new_person, name="update person")
        if person_response.status_code != 200:
            raise FlowException(person_response.json())

    @task(1)
    def delete(self):
        if len(self.created_persons) == 0:
            return
        person_id = r.choice(self.created_persons)
        person_exists = self.client.get(f'/person/{person_id}', name="exists person")
        if person_exists.status_code == 200:
            person_response = self.client.delete(f'/person/{person_id}', name="delete person")
            if person_response.status_code != 200:
                raise FlowException(person_response.json())
            else:
                self.created_persons.remove(person_id)

