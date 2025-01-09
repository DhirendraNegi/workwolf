package com.cloud.workwolf.core.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ListFactoryBean;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Data
public class Workflow {

    @Getter
    @Setter
    long id;

    @Getter@Setter
    long tenantId;

    @Getter
    @Setter
    String name;

    @Getter@Setter
    JsonNode formData;

    @Getter
    @Setter
    List<WorkflowRow> workflowMatrix = new ArrayList<WorkflowRow>();

}
