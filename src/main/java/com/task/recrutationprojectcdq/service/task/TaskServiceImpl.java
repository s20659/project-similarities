package com.task.recrutationprojectcdq.service.task;

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
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(final String id) {
        return taskRepository.findById(id);
    }

    public Task createTaskForPerson(final Person actualPerson, final Person previousPerson) {
        return createTask(actualPerson, previousPerson);
    }

    public void deleteTask(final String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByPersonId(final String personId) {
        return taskRepository.findByPersonId(personId);
    }

    private Task createTask(final Person person, final Person previousPerson){
        Task task = new Task();
        var identifier = UUID.randomUUID().toString();
        task.setIdentifier(identifier);
        task.setPerson(person);

        var taskSaved = taskRepository.save(task);
        taskSaved.start(person, previousPerson);
        return taskSaved;
    }
}
