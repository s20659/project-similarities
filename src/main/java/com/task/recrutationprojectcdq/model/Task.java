package com.task.recrutationprojectcdq.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Data
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    private int progress;

    @ElementCollection
    @CollectionTable(name = "task_results", joinColumns = @JoinColumn(name = "task_id"))
    @MapKeyColumn(name = "field_name", length = 50)
    @Enumerated(EnumType.STRING)
    @Column(name = "classification", length = 20)
    private Map<String, Classification> results = new LinkedHashMap<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

}
