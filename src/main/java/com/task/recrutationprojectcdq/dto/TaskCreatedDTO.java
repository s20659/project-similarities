package com.task.recrutationprojectcdq.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreatedDTO {

    private String taskId;

    @NotBlank(message = "Message must not be blank")
    private String message;
}
