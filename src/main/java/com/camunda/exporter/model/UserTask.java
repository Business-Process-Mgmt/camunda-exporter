package com.camunda.exporter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class UserTask {
    private int partitionId;
    private UserTaskValue value;
    private long key;
    private long timestamp;
    private String valueType;
    private String brokerVersion;
    private String rejectionType;
    private String rejectionReason;
    private int recordVersion;
    private String recordType;
    private int sourceRecordPosition;
    private String intent;
    private Authorizations authorizations;
    private int position;
}
