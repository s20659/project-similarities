package com.task.recrutationprojectcdq.service;

import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.model.Task;
import com.task.recrutationprojectcdq.repository.TaskRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class TaskService {

    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(final Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(final Person person) {
        return createTask(person);
    }

    public void deleteTask(final Long id) {
        taskRepository.deleteById(id);
    }

    private Task createTask(final Person person){
        Task task = new Task();
        var identifier = UUID.randomUUID().toString();
        task.setIdentifier(identifier);
        //task.setResult();
        task.setPerson(person);
        return task;
    }
}
