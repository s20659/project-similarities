package com.task.recrutationprojectcdq.repository;

import com.task.recrutationprojectcdq.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
