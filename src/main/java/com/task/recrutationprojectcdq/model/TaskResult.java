package com.task.recrutationprojectcdq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class TaskResult {
    private Map<String, Classification> fieldClassification;
}
