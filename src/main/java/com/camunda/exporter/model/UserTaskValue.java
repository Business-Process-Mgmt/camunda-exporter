package com.camunda.exporter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserTaskValue {
    private List<String> candidateGroupsList;
    private List<String> candidateUsersList;
    private List<String> changedAttributes;
    private long creationTimestamp;
    private String externalFormReference;
    private Map<String, Object> variables;
    private String tenantId;
    private int processDefinitionVersion;
    private String action;
    private String bpmnProcessId;
    private Map<String, String> customHeaders;
    private String elementId;
    private String assignee;
    private long processDefinitionKey;
    private String followUpDate; // ISO 8601 date string
    private long userTaskKey;
    private long elementInstanceKey;
    private long processInstanceKey;
    private String dueDate; // ISO 8601 date string
    private int formKey;
    private String bpmnElementType;
}
