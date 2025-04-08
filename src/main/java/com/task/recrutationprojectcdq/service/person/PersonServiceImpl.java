package com.task.recrutationprojectcdq.service.person;

import com.task.recrutationprojectcdq.dto.PersonDTO;
import com.task.recrutationprojectcdq.dto.UpsertResultDTO;
import com.task.recrutationprojectcdq.mapper.ApiMapper;
import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.model.Task;
import com.task.recrutationprojectcdq.repository.PersonRepository;
import com.task.recrutationprojectcdq.service.task.TaskServiceImpl;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final TaskServiceImpl taskService;

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(final String id) {
        return personRepository.findById(id);
    }

    @Transactional
    public UpsertResultDTO savePerson(final PersonDTO personDTO) {
        var person = ApiMapper.convertToPerson(personDTO);
        var personCreated = personRepository.save(person);

        final Task task = taskService.createTaskForPerson(personCreated, null);
        return new UpsertResultDTO(personCreated.getId(), task.getId(), "Person created successfully");
    }

    @Transactional
    public UpsertResultDTO updatePerson(final String id, final PersonDTO personDto) {
        Optional<Person> existingPerson = personRepository.findById(id);

        if (existingPerson.isEmpty()) {
            throw new RuntimeException("Person not found with id: " + id);
        }

        Person person = existingPerson.get();
        Person previousState = clonePerson(person);

        mapDtoToPerson(personDto, person);
        var updatedPerson = personRepository.save(person);

        Task task = taskService.createTaskForPerson(updatedPerson, previousState);

        return new UpsertResultDTO(updatedPerson.getId(), task.getId(), "Person updated successfully");
    }

    public boolean deletePerson(final String id) {
        return personRepository.findById(id)
                .map(person -> {
                    personRepository.delete(person);
                    return true;
                }).orElse(false);
    }

    private void mapDtoToPerson(PersonDTO dto, Person person) {
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setBirthDate(dto.getBirthDate());
        person.setCompany(dto.getCompany());
    }

    private Person clonePerson(Person person) {
        Person clone = new Person();
        clone.setId(person.getId());
        clone.setName(person.getName());
        clone.setSurname(person.getSurname());
        clone.setBirthDate(person.getBirthDate());
        clone.setCompany(person.getCompany());
        return clone;
    }
}
