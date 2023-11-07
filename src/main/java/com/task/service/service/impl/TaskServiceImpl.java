package com.task.service.service.impl;

import com.task.service.assembler.TaskAssembler;
import com.task.service.dto.RequestResponse;
import com.task.service.dto.TaskDTO;
import com.task.service.entity.TaskEntity;
import com.task.service.exception.TaskNotFoundException;
import com.task.service.repository.TaskRepository;
import com.task.service.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskAssembler taskAssembler;

    public RequestResponse createTask(TaskDTO taskDTO){
        RequestResponse response = new RequestResponse();
        TaskEntity taskEntity = taskAssembler.convertToEntity(taskDTO);
        try {

            taskRepository.save(taskEntity);
            response.setStatus("P");
            response.setMessage("Task created successfully");
            response.setTaskEntities(Collections.singletonList(taskEntity));
        } catch(Exception e){
            response.setStatus("F");
            response.setMessage("Task is not created, please contanct support team.");
        }
        return response;
    }

    public RequestResponse getAllTask() {
        List<TaskEntity> tasks = taskRepository.findAll();
        RequestResponse response = new RequestResponse();
        response.setStatus("P");
        response.setMessage("Successfully received all tasks, Here is the complete list");
        response.setTaskEntities(tasks);
        return response;
    }

    @Override
    public RequestResponse getAllTaskByPages(int pageNo, int pageSize, String sortBy, String sortColumn) {

        Sort sort = sortColumn.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        List<TaskEntity> tasks = taskRepository.findAll(PageRequest.of(0, pageSize)).toList();

        RequestResponse response = new RequestResponse();
        response.setStatus("P");
        response.setMessage("Successfully received all tasks, Here is the complete list");
        response.setTaskEntities(tasks);



        return response;

    }


    public RequestResponse getTaskById(Long id) {
        RequestResponse response = new RequestResponse();
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            response.setStatus("P");
            response.setMessage("Task Not Found with id : " + id);
            return response;
        }
        response.setTaskEntities(Collections.singletonList(optionalTask.get()));
        response.setStatus("P");
        response.setMessage("This is the Task with id : " + id + " is given below");
        return response;
    }


    public RequestResponse updateTaskById(TaskEntity taskEntity, Long id) {
        RequestResponse response = new RequestResponse();
        taskRepository.save(taskEntity);

        if(! taskRepository.existsById(id)){
            throw new TaskNotFoundException("Task Not Found with id  "+id);
        }
        else{
            response.setStatus("P");
            response.setMessage("Task given below with id : " + taskEntity.getId() + " updated successfully");
            response.setTaskEntities(Collections.singletonList(taskEntity));
        }


        return response;
    }

    public void deleteTaskById(Long id) {
        if(!taskRepository.existsById(id)){
            throw new TaskNotFoundException("Task Not Found with ID "+id);
        }
    }
}
