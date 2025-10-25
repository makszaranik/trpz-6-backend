package com.example.demo.service.executor.stage;

import com.example.demo.model.submission.SubmissionEntity;
import com.example.demo.service.executor.decorators.LogResultDecorator;
import com.example.demo.service.executor.decorators.PersistResultDecorator;
import com.example.demo.service.submission.SubmissionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("chain")
@RequiredArgsConstructor
public class StageExecutorChain {

    private final BuildStageExecutor buildStageExecutor;
    private final TestStageExecutor testStageExecutor;
    private final SubmissionService submissionService;

    private List<StageExecutor> stages;
    private int currentStageIndex = 0;

    @PostConstruct
    private void initExecutorChain() {
        stages = List.of(
                new LogResultDecorator(new PersistResultDecorator(buildStageExecutor, submissionService)),
                new PersistResultDecorator(testStageExecutor, submissionService)
        );
    }

    public void doNext(SubmissionEntity submission, StageExecutorChain chain) {
        if (currentStageIndex < stages.size()) {
            StageExecutor currentStage = stages.get(currentStageIndex++);
            currentStage.execute(submission, chain);
        } else {
            log.info("All stages completed.");
            currentStageIndex = 0;
        }
    }

    public void startChain(SubmissionEntity submission) {
        this.currentStageIndex = 0;
        this.doNext(submission, this);
    }

}
