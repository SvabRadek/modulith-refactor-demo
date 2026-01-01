package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.AgentId;
import com.cocroachden.modulithrefactordemo.agent.event.AgentRegistered;
import com.cocroachden.modulithrefactordemo.infrastructure.domain.TradingEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Clock;
import java.time.InstantSource;

@ApplicationModuleTest
class RegisterAgentUseCaseTest {

    @MockitoBean
    private InstantSource instantSource;

    @Autowired
    private RegisterAgentUseCase registerAgentUseCase;

    @Test
    public void itCanRegisterAgent(Scenario scenario) {
        scenario.stimulate(() -> {
                    registerAgentUseCase.handle(
                            new RegisterAgentCommand(AgentId.random(), TradingEnvironment.LIVE)
                    );
                }).forEventOfType(AgentRegistered.class)
                .toArrive();
    }
}