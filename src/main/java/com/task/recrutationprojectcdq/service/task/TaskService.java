package com.task.recrutationprojectcdq.service.task;

import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getTasks();

    Optional<Task> getTaskById(String id);

    Task createTaskForPerson(Person actualPerson, Person previousPerson);

    void deleteTask(String id);

    List<Task> getTasksByPersonId(String personId);

}
