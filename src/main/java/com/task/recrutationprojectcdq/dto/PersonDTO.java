package com.task.recrutationprojectcdq.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    @NotBlank(message = "Id must not be blank")
    private String id;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @NotBlank(message = "Surname must not be blank")
    @Size(max = 50, message = "Surname must be at most 50 characters")
    private String surname;

    @NotNull(message = "Birthdate must not be null")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Company must not be blank")
    @Size(max = 100, message = "Company must be at most 100 characters")
    private String company;
}
