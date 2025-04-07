package com.task.recrutationprojectcdq.service;

import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.repository.PersonRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PersonService {

    private PersonRepository personRepository;

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(final Integer id) {
        return personRepository.findById(id);
    }

    public Person savePerson(final Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(final Integer id) {
        personRepository.deleteById(id);
    }
}
