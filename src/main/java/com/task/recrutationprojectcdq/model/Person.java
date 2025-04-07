package com.task.recrutationprojectcdq.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(nullable = false)
    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Surname must not be blank")
    @Size(max = 50, message = "Surname must be at most 50 characters")
    private String surName;

    @Column(nullable = false)
    @NotNull(message = "Birthdate must not be null")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthDate;

    @Column(nullable = false)
    @NotBlank(message = "Company must not be blank")
    @Size(max = 100, message = "Company must be at most 100 characters")
    private String company;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

}
