package com.task.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.service.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Date dueDate;
    private Status status;
}
