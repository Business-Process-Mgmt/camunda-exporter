package com.camunda.exporter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Authorizations {
    private List<String> authorizedTenants;
}
