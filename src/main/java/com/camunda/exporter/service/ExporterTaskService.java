package com.camunda.exporter.service;

import com.camunda.exporter.model.ExporterTask;
import com.camunda.exporter.repository.ExporterTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExporterTaskService {
    @Autowired
    private ExporterTaskRepository taskRepository;

    public ExporterTask createTask(ExporterTask task) {
        return taskRepository.save(task);
    }

    public List<ExporterTask> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<ExporterTask> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public ExporterTask updateTask(Long id, ExporterTask taskDetails) {
        ExporterTask task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        // Update fields
        task.setProcessInstanceKey(taskDetails.getProcessInstanceKey());
        task.setName(taskDetails.getName());
        task.setTaskDefinitionId(taskDetails.getTaskDefinitionId());
        task.setCreationDate(taskDetails.getCreationDate());
        task.setCompletedDate(taskDetails.getCompletedDate());
        task.setTaskState(taskDetails.getTaskState());
        task.setAssigneeUser(taskDetails.getAssigneeUser());
        task.setCandidateGroups(taskDetails.getCandidateGroups());
        task.setDueDate(taskDetails.getDueDate());
        task.setAssigneeUserFullname(taskDetails.getAssigneeUserFullname());
        task.setCompletedByUser(taskDetails.getCompletedByUser());
        task.setCompletedByUserFullname(taskDetails.getCompletedByUserFullname());
        task.setChildProcessInstanceKey(taskDetails.getChildProcessInstanceKey());
        task.setProcessId(taskDetails.getProcessId());
        task.setTaskUniqueId(taskDetails.getTaskUniqueId());
        task.setDetails(taskDetails.getDetails());
        task.setCreatedBy(taskDetails.getCreatedBy());
        task.setCreatedDate(taskDetails.getCreatedDate());
        task.setModifiedBy(taskDetails.getModifiedBy());
        task.setModifiedDate(taskDetails.getModifiedDate());
        // Update other fields as needed
        return taskRepository.save(task);
    }
    public boolean updateAssignee(Long taskId, String newAssignee) {
        Optional<ExporterTask> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            ExporterTask task = taskOptional.get();
            task.setAssigneeUser(newAssignee); // Update the assignee
            taskRepository.save(task); // Save the updated task
            return true; // Update was successful
        }
        return false; // Task not found
    }
    public boolean markTaskAsCompleted(Long taskId, String completedByUser, LocalDateTime completedDateTime) {
        Optional<ExporterTask> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            ExporterTask task = taskOptional.get();
            task.setTaskState("COMPLETED"); // Set the task state to completed
            task.setCompletedByUser(completedByUser); // Update the completed by user
            task.setCompletedDate(completedDateTime); // Set the completed date
            taskRepository.save(task); // Save the updated task
            return true; // Update was successful
        }
        return false; // Task not found
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
