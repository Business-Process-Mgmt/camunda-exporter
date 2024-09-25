package com.camunda.exporter.repository;

import com.camunda.exporter.model.ExporterTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ExporterTaskRepository extends JpaRepository<ExporterTask, Long> {
}
