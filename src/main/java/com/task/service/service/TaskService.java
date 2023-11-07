package com.task.service.service;

import com.task.service.dto.RequestResponse;
import com.task.service.dto.TaskDTO;
import com.task.service.entity.TaskEntity;
import org.springframework.stereotype.Service;

public interface TaskService {

    public RequestResponse createTask(TaskDTO taskDTO);

    RequestResponse getAllTask();

    public RequestResponse getAllTaskByPages(int pageNo, int pageSize, String sortBy,String sortColumn);


    public RequestResponse getTaskById(Long id);


    public RequestResponse updateTaskById(TaskEntity taskEntity, Long id);
    public void deleteTaskById(Long id);


}
