package com.camunda.exporter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

public class CustomHeaderDetails {
    @JsonProperty("io.camunda.zeebe:candidateUsers")
    private List<String> candidateUsers;

    @JsonProperty("io.camunda.zeebe:followUpDate")
    private OffsetDateTime followUpDate;

    @JsonProperty("io.camunda.zeebe:assignee")
    private String assignee;

    @JsonProperty("io.camunda.zeebe:candidateGroups")
    private List<String> candidateGroups;

    @JsonProperty("io.camunda.zeebe:dueDate")
    private OffsetDateTime dueDate;

    // Getters and setters
    public List<String> getCandidateUsers() {
        return candidateUsers;
    }

    public void setCandidateUsers(List<String> candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    public OffsetDateTime getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(OffsetDateTime followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public List<String> getCandidateGroups() {
        return candidateGroups;
    }

    public void setCandidateGroups(List<String> candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(OffsetDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
