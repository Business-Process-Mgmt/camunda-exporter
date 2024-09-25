package com.camunda.exporter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CamundaZeebeModel {
    private int partitionId;
    private Value value;
    private long key;
    private long timestamp;

    @JsonProperty("sourceRecordPosition")
    private int sourceRecordPosition;

    @JsonProperty("valueType")
    private String valueType;

    private String intent;

    @JsonProperty("brokerVersion")
    private String brokerVersion;

    @JsonProperty("recordType")
    private String recordType;

    @JsonProperty("rejectionType")
    private String rejectionType;

    @JsonProperty("rejectionReason")
    private String rejectionReason;

    private int position;

    @Data
    @NoArgsConstructor
    public static class Value {
        private int version;
        private String bpmnEventType;
        private long flowScopeKey;
        private String bpmnElementType;
        private String bpmnProcessId;
        private String elementId;
        private long processDefinitionKey;
        private long parentProcessInstanceKey;
        private long parentElementInstanceKey;
        private long processInstanceKey;
        private String type;
    }
}
