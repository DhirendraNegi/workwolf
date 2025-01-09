package com.cloud.workwolf.core.service;

import com.cloud.workwolf.core.model.Workflow;
import com.cloud.workwolf.core.model.WorkflowRow;
import com.cloud.workwolf.core.repo.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WorkflowService {

    final
    WorkflowRepository workflowRepository;

    @Autowired
    public WorkflowService(WorkflowRepository workflowRepository) {
        workflowRepository.save(Workflow.builder().id(1).tenantId(1).name("Workflow 1")
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
                ).build());
        this.workflowRepository = workflowRepository;
    }

    public void loadWorkflowFromFile(String fileName, WorkflowRepository workflowRepository) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        // Read the file and parse it and create a workflow object
        while (bufReader.ready()) {
            String line = bufReader.readLine();
            String[] parts = line.split("::");
            List<WorkflowRow> workflowRows = new ArrayList<>();
            for (String part : parts) {
                String[] rowParts = part.split(";");
                WorkflowRow workflowRow = WorkflowRow.builder().eventHistory(rowParts[0]).event(rowParts[1]).nextState(rowParts[2]).build();
                workflowRows.add(workflowRow);
            }
            Workflow workflow = Workflow.builder().id(1).tenantId(1).name("Workflow 1").workflowMatrix(workflowRows).build();
            workflowRepository.save(workflow);
        }
    }

    public Workflow getWorkflow(long id) {
        return workflowRepository.findById(id).orElseThrow(() -> new RuntimeException("Workflow not found"));
    }

    public List<Workflow> getWorkflows() {
        return workflowRepository.findAll();
    }

    public void saveWorkflow(Workflow workflow) {
        workflowRepository.save(workflow);
    }

    public void optimize(Workflow workflow) {
    }
}
