package com.camunda.exporter.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "task")
@Data
public class ExporterTask {
    @Id
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "process_instance_key")
    private Long processInstanceKey;

    @Column(name = "name")
    private String name;

    @Column(name = "task_definition_id")
    private String taskDefinitionId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate; // Changed to LocalDateTime

    @Column(name = "completed_date")
    private LocalDateTime completedDate; // Changed to LocalDateTime

    @Column(name = "task_state")
    private String taskState;

    @Column(name = "assignee_user")
    private String assigneeUser;

    @Column(name = "candidate_groups")
    private String candidateGroups;

    @Column(name = "due_date")
    private LocalDateTime dueDate; // Changed to LocalDateTime

    @Column(name = "assigneee_user_fullname")
    private String assigneeUserFullname;

    @Column(name = "completed_by_user")
    private String completedByUser;

    @Column(name = "completed_by_user_fullname")
    private String completedByUserFullname;

    @Column(name = "child_process_instance_key")
    private Long childProcessInstanceKey;

    @Column(name = "process_id")
    private Long processId;

    @Column(name = "task_unique_id")
    private String taskUniqueId;

    @Column(name = "details")
    private String details;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate; // Changed to LocalDateTime

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate; //
}
