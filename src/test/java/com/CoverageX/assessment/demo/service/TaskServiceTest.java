package com.CoverageX.assessment.demo.service;

import com.CoverageX.assessment.demo.api.dto.ResponseDto;
import com.CoverageX.assessment.demo.api.dto.TaskDto;
import com.CoverageX.assessment.demo.entity.Task;
import com.CoverageX.assessment.demo.enums.TaskStatus;
import com.CoverageX.assessment.demo.exception.ServiceException;
import com.CoverageX.assessment.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Test
    void saveTask() {
        TaskDto taskDto = new TaskDto();
        Task task = new Task();
        task.setId(1L);
        assertNotNull(taskService.saveTask(taskDto));
    }

    @Test
    void getAllTasks() {
        Mockito.when(taskRepository.getAllTasks(PageRequest.of(0,5))).thenReturn(new ArrayList<>());
        assertNotNull(taskService.getAllTasks());
    }

    @Test
    void updateTaskStatus_ShouldUpdateStatus_WhenTaskExists() {
        Long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        task.setTaskStatus(TaskStatus.TODO);

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        ResponseDto response = taskService.updateTaskStatus(taskId);

        assertNotNull(response);
    }

    @Test
    void updateTaskStatus_ShouldThrowException_WhenTaskNotFound() {
        Long taskId = 99L;
        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class,
                () -> taskService.updateTaskStatus(taskId));

        assertEquals("Task not found", exception.getMessage());
    }
}