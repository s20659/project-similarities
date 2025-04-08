package com.task.recrutationprojectcdq.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private String identifier;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    @Transient
    private AtomicInteger progress = new AtomicInteger(0);

    @ElementCollection
    @CollectionTable(name = "task_results", joinColumns = @JoinColumn(name = "task_id"))
    @MapKeyColumn(name = "field_name", length = 50)
    @Enumerated(EnumType.STRING)
    @Column(name = "classification", length = 20)
    private Map<String, Classification> result = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

    @Async
    @Transactional
    public void start(Person previous, Person current) {
        this.status = TaskStatus.IN_PROGRESS;
        this.person = current;
        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(1000);
                    this.progress.set(i * 10);
                }
                this.result = TaskProcessor.comparePeople(previous, current);
                this.status = TaskStatus.COMPLETED;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
