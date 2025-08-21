package com.CoverageX.assessment.demo.service;

import com.CoverageX.assessment.demo.api.dto.ResponseDto;
import com.CoverageX.assessment.demo.api.dto.TaskDto;
import com.CoverageX.assessment.demo.entity.Task;
import com.CoverageX.assessment.demo.enums.TaskStatus;
import com.CoverageX.assessment.demo.exception.ServiceException;
import com.CoverageX.assessment.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public ResponseDto saveTask(TaskDto taskDto){
        Task task = Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .taskStatus(TaskStatus.TODO)
                .build();
        taskRepository.save(task);
        return new ResponseDto("Task created");
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.getAllTasks(PageRequest.of(0,5));
    }

    public ResponseDto updateTaskStatus(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new
                ServiceException("Task not found","Bad request", HttpStatus.BAD_REQUEST));
        task.setTaskStatus(TaskStatus.DONE);
        taskRepository.save(task);
        return new ResponseDto("Task status updated");
    }
}
