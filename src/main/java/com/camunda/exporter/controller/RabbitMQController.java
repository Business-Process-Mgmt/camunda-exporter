package com.camunda.exporter.controller;

import com.camunda.exporter.service.RabbitMQListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {
    @Autowired
    private RabbitMQListenerService rabbitMQService;

    @DeleteMapping("/queues/purge/{queueName}")
    public ResponseEntity<String> purgeQueue(@PathVariable String queueName) {
        rabbitMQService.purgeQueue(queueName);
        return ResponseEntity.ok("Queue " + queueName + " has been purged.");
    }
}
