package com.example.demo.service.executor.decorators;

import com.example.demo.model.submission.SubmissionEntity;
import com.example.demo.service.executor.stage.StageExecutor;
import com.example.demo.service.executor.stage.StageExecutorChain;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogResultDecorator extends StageResultDecorator {

    public LogResultDecorator(StageExecutor wrapped) {
        super(wrapped);
    }

    @Override
    public void execute(SubmissionEntity submission, StageExecutorChain chain) {
        log.info("submission with id: {}, saved with status: {}", submission.getId(), submission.getStatus());
        super.execute(submission, chain);
    }

}
