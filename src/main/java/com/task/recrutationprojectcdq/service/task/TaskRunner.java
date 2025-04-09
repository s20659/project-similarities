package com.task.recrutationprojectcdq.service.task;

import com.task.recrutationprojectcdq.model.Person;
import com.task.recrutationprojectcdq.model.Task;
import com.task.recrutationprojectcdq.model.TaskStatus;
import com.task.recrutationprojectcdq.repository.TaskRepository;
import com.task.recrutationprojectcdq.util.TaskProcessor;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskRunner {

    private final TaskRepository taskRepository;

    @Async("taskExecutor")
    protected void runTaskAsync(final Task task, final Person previous, final Person current) {
        task.setStatus(TaskStatus.IN_PROGRESS);
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                task.setProgress(i * 10);
                taskRepository.save(task);
                System.out.println(task.getProgress() + "%");
            } catch (InterruptedException e) {
                task.setStatus(TaskStatus.FAILED);
                taskRepository.save(task);
                Thread.currentThread().interrupt();
            }
        }
        task.setResults(TaskProcessor.comparePeople(previous, current));
        task.setStatus(TaskStatus.COMPLETED);
        taskRepository.save(task);
    }

}
