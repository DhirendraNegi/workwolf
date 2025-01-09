package com.example;

import com.cloud.workwolf.core.model.Workflow;
import com.cloud.workwolf.core.model.WorkflowRow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class SalesApprovalProcessTest {

    Workflow testWorkFlow;

    @Test
    void contextLoads() {
        testWorkFlow = Workflow.builder().id(1).tenantId(1).name("Hiring Process")
                .workflowMatrix(
                        Arrays.asList(
                                WorkflowRow.builder().eventHistory("Start::").event("Submit").nextState("Submitted").build(),
                                WorkflowRow.builder().eventHistory("Start:user.role==CXO:").event("Submit").nextState("Approved").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted").event("Approve").nextState("Approved").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted").event("Reject").nextState("Rejected").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted::Approved").event("Publish").nextState("Published").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted::Rejected").event("Submit").nextState("Submitted").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted::Rejected::Submitted").event("Approve").nextState("Approved").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted::Rejected::Submitted::Published").event("Archive").nextState("Archived").build(),
                                WorkflowRow.builder().eventHistory("Start::Submitted::Approved::Published").event("Archive").nextState("Archived").build()
                        )
                ).build();
    }
}
