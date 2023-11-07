package com.task.service.dto;

import com.task.service.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponse {

    private String status;
    private String message;
    private List<TaskEntity> taskEntities;



}
