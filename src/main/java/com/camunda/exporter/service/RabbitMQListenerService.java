package com.camunda.exporter.service;

import com.camunda.exporter.model.CamundaZeebeModel;
import com.camunda.exporter.model.ExporterTask;
import com.camunda.exporter.util.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.operate.exception.OperateException;
import io.camunda.tasklist.exception.TaskListException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            CamundaZeebeModel model = objectMapper.readValue(message, CamundaZeebeModel.class);

            String type=model.getValue().getType();
            String intent=model.getIntent();


            if(type!=null && type.equals("io.camunda.zeebe:userTask")) {
                System.out.println("intent::"+intent);
                if(intent!=null && intent.equals("CREATED")) {
                    Long taskId = model.getKey();
                    System.out.println("taskId: " + taskId);
                    ExporterTask exporterTask = camundaTaskService.getTaskInformation(taskId);
                    System.out.println("exporterTask TaskId: " + exporterTask.getTaskId());
                    exporterTaskService.createTask(exporterTask);
                } if(intent!=null && intent.equals("COMPLETED")) {
                    Long taskId = model.getKey();
                    System.out.println("taskId: " + taskId);
                    ExporterTask exporterTask = camundaTaskService.getTaskInformation(taskId);
                    exporterTask.setTaskState("COMPLETED");
                    exporterTask.setCompletedDate(DateUtils.convertTimesStampTODate(model.getTimestamp()));
                    System.out.println("exporterTask TaskId: " + exporterTask.getTaskId());
                    ExporterTask updateTask=exporterTaskService.updateTask(exporterTask.getTaskId(),exporterTask);
                }
            }
        } catch (JsonProcessingException | TaskListException | OperateException e) {
            System.out.println("exception::"+e.getMessage());
            throw new RuntimeException(e);
        }
        // Process your message here
    }
    public void purgeQueue(String queueName) {
        rabbitTemplate.execute(channel -> {
            channel.queuePurge(queueName);
            return null;
        });
    }
}
