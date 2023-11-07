package com.task.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "task_entity" )
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "task_title")
    @NotNull(message = "title Should not be blank")
    private String title;

    @Column(name = "task_description")
    @NotNull(message = "description should not be blank")
    private String description;

    @Column(name = "task_dueDate")
    @NotNull(message = "due Date should not be blank")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dueDate;

    @Column(name = "task_status")
    @NotNull(message = "status should not be blank")
    private Status status;

//    public TaskEntity(){}
//    public TaskEntity(Long id, String title, String description, Date dueDate, Status status) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.dueDate = dueDate;
//        this.status = status;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Date getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(Date dueDate) {
//        this.dueDate = dueDate;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }


    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                '}';
    }
}
