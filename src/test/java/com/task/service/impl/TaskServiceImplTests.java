package com.task.service.impl;

import com.task.service.dto.RequestResponse;
import com.task.service.dto.TaskDTO;
import com.task.service.entity.Status;
import com.task.service.entity.TaskEntity;
import com.task.service.exception.TaskNotFoundException;
import com.task.service.repository.TaskRepository;
import com.task.service.service.TaskService;
import com.task.service.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskServiceImplTests {

    @InjectMocks
    TaskService taskService = new TaskServiceImpl();

    private ModelMapper modelMapper;

    @Mock
    TaskRepository repository;
     @Test
    public void createTask_Test(){

         TaskEntity taskEntity = new TaskEntity();
         taskEntity.setId(10L);
         taskEntity.setStatus(Status.valueOf("PENDING"));
         taskEntity.setDescription("test details are written");
         taskEntity.setTitle("Java test cases");

         TaskDTO taskDTO = modelMapper.map(taskEntity,TaskDTO.class);
         RequestResponse requestResponse = taskService.createTask(taskDTO);
         when(repository.save(taskEntity)).thenReturn(null);
         assertNotEquals(requestResponse.getMessage(),nullValue());
     }
}
