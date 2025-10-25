package com.example.demo.service.executor.decorators;


import com.example.demo.model.submission.SubmissionEntity;
import com.example.demo.service.executor.stage.StageExecutor;
import com.example.demo.service.executor.stage.StageExecutorChain;
import com.example.demo.service.submission.SubmissionService;

public class PersistResultDecorator extends StageResultDecorator {

    private final SubmissionService submissionService;

    public PersistResultDecorator(StageExecutor wrapped, SubmissionService submissionService) {
        super(wrapped);
        this.submissionService = submissionService;
    }

    @Override
    public void execute(SubmissionEntity submission, StageExecutorChain chain) {
        submissionService.save(submission);
        super.execute(submission, chain);
    }
}
