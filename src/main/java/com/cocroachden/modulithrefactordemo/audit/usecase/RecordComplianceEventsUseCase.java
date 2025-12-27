package com.cocroachden.modulithrefactordemo.audit.usecase;

import com.cocroachden.modulithrefactordemo.fill.event.FillRecorded;
import com.cocroachden.modulithrefactordemo.agent.event.AgentRegistered;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import org.springframework.modulith.events.ApplicationModuleListener;

@UseCase
public class RecordComplianceEventsUseCase {

    @ApplicationModuleListener
    public void handle(FillRecorded event) {
        // save event
    }

    @ApplicationModuleListener
    public void handle(AgentRegistered agentRegistered) {
        // save event
    }

}
