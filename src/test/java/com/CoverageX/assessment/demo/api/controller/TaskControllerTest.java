package com.CoverageX.assessment.demo.api.controller;

import com.CoverageX.assessment.demo.api.dto.ResponseDto;
import com.CoverageX.assessment.demo.api.dto.TaskDto;
import com.CoverageX.assessment.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @InjectMocks
    TaskController taskController;

    @Mock
    TaskService taskService;

    @Test
    void saveTask() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        Mockito.when(taskService.saveTask(taskDto)).thenReturn(new ResponseDto());
        assertNotNull(taskController.saveTask(taskDto));
    }

    @Test
    void getAllTasks() {
        Mockito.when(taskService.getAllTasks()).thenReturn(new ArrayList<>());
        assertNotNull(taskController.getAllTasks());
    }

    @Test
    void updateTaskStatus() {
        Long id = 1L;
        Mockito.when(taskService.updateTaskStatus(id)).thenReturn(new ResponseDto());
        assertNotNull(taskController.updateTaskStatus(id));
    }
}