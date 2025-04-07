package com.task.recrutationprojectcdq.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long taskId;

    @Column(nullable = false, unique = true)
    String identifier;

    @Column(nullable = false)
    TaskStatus status;

    @Column(nullable = false)
    String result;

    @ManyToOne
    @JoinColumn(name = "personId", nullable = false)
    Person person;
}
