package com.task.recrutationprojectcdq.controller;

import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.service.PersonService;
import com.task.recrutationprojectcdq.service.TaskService;
import lombok.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
@Value
public class PersonController {

    PersonService personService;

    TaskService taskService;

    @GetMapping
    public List<Person> getPeople() {
        return personService.getPeople();
    }

    @GetMapping("/{id}")
    public Optional<Person> getOrderById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        var createdPerson = personService.savePerson(person);
        taskService.saveTask(createdPerson);
        return createdPerson;
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
