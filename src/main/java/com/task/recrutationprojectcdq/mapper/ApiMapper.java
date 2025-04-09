package com.task.recrutationprojectcdq.mapper;

import com.task.recrutationprojectcdq.dto.PersonDTO;
import com.task.recrutationprojectcdq.dto.TaskDTO;
import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.model.Task;

public class ApiMapper {

    public static Person convertToPerson(final PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setBirthDate(personDTO.getBirthDate());
        person.setCompany(personDTO.getCompany());
        return person;
    }

    public static TaskDTO convertToTaskDTO(final Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setProgress(task.getProgress());
        taskDTO.setPersonId(task.getPerson().getId());
        taskDTO.setResults(task.getResults());
        return taskDTO;
    }
}
