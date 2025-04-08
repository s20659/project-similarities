package com.task.recrutationprojectcdq.controller;

import com.task.recrutationprojectcdq.dto.PersonDTO;
import com.task.recrutationprojectcdq.dto.TaskCreatedDTO;
import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.service.person.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        return ResponseEntity.ok(personService.getPeople());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable final String id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskCreatedDTO> createPerson(@RequestBody @Valid final PersonDTO personDTO) {
        TaskCreatedDTO response = personService.savePerson(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCreatedDTO> updatePerson(@PathVariable final String id,
                                                       @Valid @RequestBody final PersonDTO personDto) {
        try {
            TaskCreatedDTO response = personService.updatePerson(id, personDto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable final String id) {
        personService.deletePerson(id);
    }
}
