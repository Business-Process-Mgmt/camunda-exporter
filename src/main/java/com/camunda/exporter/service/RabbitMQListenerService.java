package com.camunda.exporter.service;

import com.camunda.exporter.constants.Constants;
import com.camunda.exporter.model.ExporterTask;
import com.camunda.exporter.model.UserTask;
import com.camunda.exporter.util.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RabbitMQListenerService {
    @Autowired
    CamundaTaskService camundaTaskService;
    @Autowired
    ExporterTaskService exporterTaskService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "camundaQueue",exclusive = false)
    public void receiveMessage(String message) {
        System.out.println("Received Message: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            UserTask userTask = objectMapper.readValue(message, UserTask.class);

            String valueType=userTask.getValueType();
            String intent=userTask.getIntent();


            if(valueType!=null && valueType.equals("USER_TASK")) {
                System.out.println("intent::"+intent);
                if(intent!=null && intent.equals("CREATED")) {
                    ExporterTask exporterTask = new ExporterTask();
                    taskMapping(exporterTask,userTask);
                    /*Long taskId = userTask.getKey();
                    System.out.println("taskId: " + taskId);
                    exporterTask.setProcessId(model.getValue().getBpmnProcessId());
                    Object customHeaders=model.getValue().getCustomHeaders();
                    System.out.println("exporterTask customHeaders: " + customHeaders);
                    String validJson = convertToValidJson(customHeaders.toString());
                    System.out.println("exporterTask validJson: " + validJson);
                     System.out.println("exporterTask TaskId: " + exporterTask.getTaskId());*/
                    exporterTaskService.createTask(exporterTask);
                } else if(intent!=null && intent.equals("COMPLETED")) {
                    /*Long taskId = model.getKey();
                    System.out.println("taskId: " + taskId);
                    ExporterTask exporterTask = new ExporterTask();
                    exporterTask.setTaskState("COMPLETED");
                    exporterTask.setCompletedDate(DateUtils.convertTimesStampTODate(model.getTimestamp()));
                    exporterTask.setCompletedByUser(exporterTask.getAssigneeUser());
                    System.out.println("exporterTask TaskId: " + exporterTask.getTaskId());
                    ExporterTask updateTask=exporterTaskService.updateTask(exporterTask.getTaskId(),exporterTask);
               */
                }
            }
        } catch (JsonProcessingException e) {
            System.out.println("exception::"+e.getMessage());
            throw new RuntimeException(e);
        }
        // Process your message here
    }

    private void taskMapping(ExporterTask exporterTask, UserTask userTask) {
        exporterTask.setTaskId(userTask.getValue().getUserTaskKey());
        exporterTask.setProcessInstanceKey(userTask.getValue().getProcessInstanceKey());
        exporterTask.setTaskDefinitionId(userTask.getValue().getElementId());
        exporterTask.setCreationDate(DateUtils.convertTimesStampTODate(userTask.getTimestamp()));
        exporterTask.setTaskState(Constants.TASK_STATE_CREATED);
        exporterTask.setAssigneeUser(userTask.getValue().getAssignee());
        exporterTask.setCandidateGroups(userTask.getValue().getCandidateGroupsList().toString());
        exporterTask.setDueDate(LocalDateTime.parse(userTask.getValue().getDueDate().replace("Z", ""), DateTimeFormatter.ISO_DATE_TIME));
        exporterTask.setProcessId(userTask.getValue().getBpmnProcessId());
        exporterTask.setCreatedDate(DateUtils.getCurrentDateTimeStamp());
        exporterTask.setCreatedBy("System");
        exporterTask.setModifiedDate(DateUtils.getCurrentDateTimeStamp());
        exporterTask.setModifiedBy("System");
    }

    public void purgeQueue(String queueName) {
        rabbitTemplate.execute(channel -> {
            channel.queuePurge(queueName);
            return null;
        });
    }
    private static String convertToValidJson(String input) {
        // Replace '=' with ':'
        input = input.replace("=", ":");

        // Replace '{' and '}' to ensure they are formatted correctly
        input = input.replace("{", "{\"").replace("}", "\"}");

        // Replace any instance of key without quotes
        input = input.replaceAll("(\\w[\\w.]*):", "\"$1\":");

        return input;
    }
}
