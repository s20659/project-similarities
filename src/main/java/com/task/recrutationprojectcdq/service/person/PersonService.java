package com.task.recrutationprojectcdq.service.person;

import com.task.recrutationprojectcdq.dto.PersonDTO;
import com.task.recrutationprojectcdq.dto.UpsertResultDTO;
import com.task.recrutationprojectcdq.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> getPeople();

    Optional<Person> getPersonById(String id);

    UpsertResultDTO savePerson(PersonDTO personDTO);

    UpsertResultDTO updatePerson(final String id, final PersonDTO personDto);

    boolean deletePerson(String id);
}
