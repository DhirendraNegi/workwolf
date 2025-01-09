package com.cloud.workwolf.core.model;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Builder
public class EventHistory {

    @Getter@Setter
    long tenantId;

    @Getter@Setter
    private String id;

    @Getter@Setter
    private String eventHistory;

    @Getter@Setter
    private String workId;

    @Getter@Setter
    private String workflowId;

    @Getter@Setter
    private LocalDateTime startTime;

    @Getter@Setter
    private HashMap<String,File> files = new HashMap<>();

    @Getter@Setter
    private HashMap<String, JsonNode> eventData = new HashMap<>();

}
