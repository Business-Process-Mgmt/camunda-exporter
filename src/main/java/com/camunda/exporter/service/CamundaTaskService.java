package com.camunda.exporter.service;

import com.camunda.exporter.model.ExporterTask;
import com.camunda.exporter.util.DateUtils;
import org.springframework.stereotype.Service;
import io.camunda.common.auth.*;
import io.camunda.operate.CamundaOperateClient;
import io.camunda.operate.exception.OperateException;
import io.camunda.operate.model.Variable;
import io.camunda.operate.search.Filter;
import io.camunda.operate.search.SearchQuery;
import io.camunda.operate.search.VariableFilter;
import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.dto.*;
import io.camunda.tasklist.exception.TaskListException;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CamundaTaskService {
    public  ExporterTask getTaskInformation(Long taskId) throws TaskListException, OperateException {
        ExporterTask exporterTask=new ExporterTask();
        SimpleConfig simpleConf = new SimpleConfig();
        simpleConf.addProduct(Product.TASKLIST, new SimpleCredential("http://localhost:7082", "demo", "demo"));
        Authentication auth = SimpleAuthentication.builder().withSimpleConfig(simpleConf).build();

        CamundaTaskListClient client = CamundaTaskListClient.builder()
                .taskListUrl("http://localhost:7082")
                .authentication(auth)
                .cookieExpiration(Duration.ofSeconds(5))
                .build();
        System.out.println("taskId inside getTaskInformation::"+taskId);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Task task=client.getTask(String.valueOf(taskId));
        System.out.println("taskList size::"+task.getProcessInstanceKey());
        System.out.println("task Id::"+task.getId());
        System.out.println("process Instance Key::"+task.getProcessInstanceKey());
        System.out.println("task Name::"+task.getName());
        System.out.println("task Definition Id::"+task.getTaskDefinitionId());
        System.out.println("task Creation Date::"+task.getCreationDate());
        System.out.println("task Completion  Date::"+task.getCompletionDate());
        System.out.println("task State::"+task.getTaskState());
        System.out.println("assignee user::"+task.getAssignee());
        exporterTask.setTaskId(Long.valueOf(task.getId()));
        exporterTask.setProcessInstanceKey(Long.valueOf(task.getProcessInstanceKey()));
        exporterTask.setName(task.getName());
        exporterTask.setTaskDefinitionId(task.getTaskDefinitionId());
        OffsetDateTime offsetCreatedDateTime = OffsetDateTime.parse(task.getCreationDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        exporterTask.setCreationDate(offsetCreatedDateTime.toLocalDateTime());
        exporterTask.setTaskState(TaskState.CREATED.getRawValue());
        exporterTask.setAssigneeUser(task.getAssignee());
        exporterTask.setCreatedDate(DateUtils.getCurrentDateTimeStamp());
        exporterTask.setCreatedBy("System");
        exporterTask.setModifiedDate(DateUtils.getCurrentDateTimeStamp());
        exporterTask.setModifiedBy("System");

        return exporterTask;
    }
}
