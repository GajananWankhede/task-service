package com.task.service.controller;


import com.task.service.dto.RequestResponse;
import com.task.service.dto.TaskDTO;
import com.task.service.entity.TaskEntity;
import com.task.service.service.TaskService;
import com.task.service.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



//Swagger-UI --->     http://localhost:8051/swagger-ui.html



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/task-service")
public class TaskController {

    @Autowired
    private TaskService taskService;
    //Creare a new task

    @PostMapping("/create-task")
    public ResponseEntity<RequestResponse> createTask(@RequestBody @Valid TaskDTO taskDTO){
        RequestResponse requestResponse = taskService.createTask(taskDTO);
        return new ResponseEntity<>(requestResponse, HttpStatus.CREATED);
    }

    //Retrive all tasks

    @GetMapping("all-task")
    public ResponseEntity<RequestResponse> getAllTask() {
        RequestResponse response = taskService.getAllTask();
        return ResponseEntity.ok(response);
    }

//pageNo, int pageSize, String sortBy, String sortColumn
    @GetMapping("allTaskByPage")
    public ResponseEntity<RequestResponse> getAllTaskByPage(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                                            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                                            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortColumn
                                                            ) {
        RequestResponse response = taskService.getAllTaskByPages(pageNo,pageSize,sortBy,sortColumn);
        return ResponseEntity.ok(response);
    }

    //Retrive task by ID

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<RequestResponse> getTaskById(@PathVariable Long id)
    {
        RequestResponse response = taskService.getTaskById(id);
        if (response.getTaskEntities() == null)
        {
            return ResponseEntity.ok(response);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Update a task(title,description,dueDate,status)


    @PutMapping("/updateTaskById/{id}")
    public ResponseEntity<String> updateUserById(@RequestBody TaskEntity updatedUser, @PathVariable Long id){
        try{
            taskService.updateTaskById(updatedUser,id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task with id " + id + " updated successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    //Delete a task

    @DeleteMapping("/deleteTaskById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        try {
            taskService.deleteTaskById(id);
            return ResponseEntity.ok("task with id : " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
