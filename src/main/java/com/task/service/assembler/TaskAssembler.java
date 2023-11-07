package com.task.service.assembler;

import com.task.service.dto.TaskDTO;
import com.task.service.entity.TaskEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskAssembler {

    @Autowired
    ModelMapper modelMapper;
    public TaskDTO convertToDto(TaskEntity taskEntity){
        TaskDTO taskDTO = modelMapper.map(taskEntity,TaskDTO.class);

        return taskDTO;
    }

    public TaskEntity convertToEntity(TaskDTO taskDTO){
        TaskEntity taskEntity = modelMapper.map(taskDTO,TaskEntity.class);

        return taskEntity;
    }
}
