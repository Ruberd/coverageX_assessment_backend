package com.CoverageX.assessment.demo.api.controller;

import com.CoverageX.assessment.demo.api.dto.ResponseDto;
import com.CoverageX.assessment.demo.api.dto.TaskDto;
import com.CoverageX.assessment.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseDto saveTask(@RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public ResponseDto updateTaskStatus(@PathVariable(value = "id") Long id) {
        return taskService.updateTaskStatus(id);
    }
}
