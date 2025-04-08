package com.task.recrutationprojectcdq.dto;

import com.task.recrutationprojectcdq.model.Classification;
import com.task.recrutationprojectcdq.model.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String id;

    private TaskStatus status;

    private int progress;

    @NotNull(message = "Person id must not be null")
    private String personId;

    @NotNull(message = "Results must not be null")
    private Map<String, Classification> results;

}
