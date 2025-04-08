package com.task.recrutationprojectcdq.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task.recrutationprojectcdq.util.TaskProcessor;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Data
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    @Transient
    private AtomicInteger progress = new AtomicInteger(0);

    @ElementCollection
    @CollectionTable(name = "task_results", joinColumns = @JoinColumn(name = "task_id"))
    @MapKeyColumn(name = "field_name", length = 50)
    @Enumerated(EnumType.STRING)
    @Column(name = "classification", length = 20)
    private Map<String, Classification> results = new HashMap<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    @Async
    @Transactional
    public void processTaskAsync(Person previous, Person current) {
        this.status = TaskStatus.IN_PROGRESS;
        try {
            for (int i = 1; i <= 10; i++) {
                Thread.sleep(10);
                this.progress.set(i * 10);
                System.out.println(this.progress.get() + "%");
            }
            this.results = TaskProcessor.comparePeople(previous, current);
            this.status = TaskStatus.COMPLETED;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
