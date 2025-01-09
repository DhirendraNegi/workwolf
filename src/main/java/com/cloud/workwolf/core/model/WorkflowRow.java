package com.cloud.workwolf.core.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class WorkflowRow {

    @Getter@Setter
    private String eventHistory;

    @Getter@Setter
    private String event;

    @Getter@Setter
    private String nextState;

    public WorkflowRow(String eventHistory, String event, String nextState) {
        this.eventHistory = eventHistory;
        this.event = event;
        this.nextState = nextState;
    }
}
