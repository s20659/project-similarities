package com.task.recrutationprojectcdq.repository;

import com.task.recrutationprojectcdq.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByPersonId(String personId);
}
