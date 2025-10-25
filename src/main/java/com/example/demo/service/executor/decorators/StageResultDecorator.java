package com.example.demo.service.executor.decorators;

import com.example.demo.model.submission.SubmissionEntity;
import com.example.demo.service.executor.stage.StageExecutor;
import com.example.demo.service.executor.stage.StageExecutorChain;


public abstract class StageResultDecorator implements StageExecutor {

    private final StageExecutor wrapped;

    StageResultDecorator(StageExecutor wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void execute(SubmissionEntity submission, StageExecutorChain chain) {
        wrapped.execute(submission, chain);
    }
}
