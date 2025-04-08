package com.task.recrutationprojectcdq.service.person;

import com.task.recrutationprojectcdq.dto.PersonDTO;
import com.task.recrutationprojectcdq.dto.TaskCreatedDTO;
import com.task.recrutationprojectcdq.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getPeople();

    Optional<Person> getPersonById(String id);

    TaskCreatedDTO savePerson(PersonDTO personDTO);

    TaskCreatedDTO updatePerson(final String id, final PersonDTO personDto);

    void deletePerson(String id);
}
