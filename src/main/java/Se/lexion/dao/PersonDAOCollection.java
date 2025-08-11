package Se.lexion.dao;

import Se.lexion.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDAOCollection implements PersonDAO{
    private List<Person> persons;

    public PersonDAOCollection() {
        this.persons = new ArrayList<>();
    }

    @Override
    public Person persist(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        // Check if id already exists
        if (findById(person.getId()) != null) {
            throw new IllegalArgumentException("Person with this ID already exists");
        }
        persons.add(person);
        return person;
    }

    @Override
    public Person findById(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person findByEmail(String email) {
        if (email == null) {
            return null;
        }
        return persons.stream()
                .filter(person -> email.equals(person.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public void remove(int id) {
        persons.removeIf(person -> person.getId() == id);
    }
}
