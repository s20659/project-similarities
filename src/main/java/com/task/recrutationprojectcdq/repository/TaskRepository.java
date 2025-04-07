package com.task.recrutationprojectcdq.repository;

import com.task.recrutationprojectcdq.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
