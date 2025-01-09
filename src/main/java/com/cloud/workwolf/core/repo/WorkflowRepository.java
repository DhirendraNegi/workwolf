package com.cloud.workwolf.core.repo;

import com.cloud.workwolf.core.model.Workflow;
import org.springframework.stereotype.Service;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@Repository
@Service
public class WorkflowRepository {

    List<Workflow> workflows = new ArrayList<>();
    public Optional<Workflow> findById(long id) {
        return workflows.stream().filter(workflow -> workflow.getId() == id).findFirst();
    }

    public List<Workflow> findAll() {
        return workflows;
    }

    public void save(Workflow workflow) {
        workflows.add(workflow);
    }

}
