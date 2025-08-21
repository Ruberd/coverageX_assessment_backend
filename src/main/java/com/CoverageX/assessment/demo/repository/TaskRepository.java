package com.CoverageX.assessment.demo.repository;

import com.CoverageX.assessment.demo.api.dto.TaskDto;
import com.CoverageX.assessment.demo.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT NEW com.CoverageX.assessment.demo.api.dto.TaskDto(t.id,t.title,t.description) " +
           "FROM Task t " +
           "WHERE t.taskStatus = 'TODO' " +
           "ORDER BY t.id DESC")
    List<TaskDto> getAllTasks(Pageable pageable);
}
