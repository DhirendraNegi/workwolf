package com.cloud.workwolf.core.controller;

import com.cloud.workwolf.core.model.Workflow;
import com.cloud.workwolf.core.model.WorkflowRow;
import com.cloud.workwolf.core.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/{tenantContext}/workflows")
public class WorkflowController {

    WorkflowService workflowService;

    @Autowired
    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity getWorkflows() {
        System.out.println("getWorkflows");
        return new ResponseEntity<>(workflowService.getWorkflows(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity addWorkflow( @RequestBody Workflow workflow ) {
        workflowService.saveWorkflow(workflow);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/workflow/{id}")
    public ResponseEntity getWorflow(@PathVariable long id) {
        return new ResponseEntity<>(workflowService.getWorkflow(id), HttpStatus.OK);
    }

    @PutMapping("/workflow/{id}")
    public ResponseEntity updateWorkflow(@PathVariable long id, Workflow workflow) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/workflow/{id}/optimize")
    public ResponseEntity optimizeWorkflow(@PathVariable long id ) {
        Workflow workflow = this.workflowService.getWorkflow(id);
        workflowService.optimize(workflow);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping(value = "/workflow/{id}/steps",consumes = "application/json")
    public ResponseEntity addStep(@PathVariable long id, WorkflowRow workflowRow) {
        return new ResponseEntity<>(workflowService.getWorkflow(id).getWorkflowMatrix().add(workflowRow),
                HttpStatus.CREATED);
    }


}
