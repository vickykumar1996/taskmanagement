package com.ty.btm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.btm.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
