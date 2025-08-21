package com.CoverageX.assessment.demo.enums;

import com.CoverageX.assessment.demo.exception.ServiceException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public enum TaskStatus {
    TODO("Todo"),
    DONE("Done");

    private final String mappedValue;

    TaskStatus(String mappedValue) {
        this.mappedValue = mappedValue;
    }

    public static TaskStatus fromMappedValue(String mappedValue) {
        if (mappedValue == null || mappedValue.isBlank()) {
            return null;
        }
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.mappedValue.equalsIgnoreCase(mappedValue)) {
                return taskStatus;
            }
        }
        throw new ServiceException("Unsupported type" + mappedValue, "Bad request", HttpStatus.BAD_REQUEST);
    }


    public String getMappedValue() {
        return mappedValue;
    }

    public static List<String> getAll() {
        List<String> list = new ArrayList<>();
        for (TaskStatus taskStatus : TaskStatus.values()) {
            list.add(taskStatus.mappedValue);
        }
        return list;
    }

    public static List<TaskStatus> getAllEnum() {
        List<TaskStatus> list = new ArrayList<>();
        for (TaskStatus taskStatus : TaskStatus.values()) {
            list.add(taskStatus);
        }
        return list;
    }
}
