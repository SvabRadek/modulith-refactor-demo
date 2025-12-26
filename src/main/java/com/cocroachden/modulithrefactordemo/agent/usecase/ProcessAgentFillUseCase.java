package com.cocroachden.modulithrefactordemo.agent.usecase;

import com.cocroachden.modulithrefactordemo.agent.domain.AgentFill;
import com.cocroachden.modulithrefactordemo.agent.event.AgentFillReceived;
import com.cocroachden.modulithrefactordemo.agent.repository.AgentRepository;
import com.cocroachden.modulithrefactordemo.agent.utils.AgentUtils;
import com.cocroachden.modulithrefactordemo.account.domain.RecordedFill;
import com.cocroachden.modulithrefactordemo.account.usecase.RecordFillForm;
import com.cocroachden.modulithrefactordemo.account.usecase.RecordFillUseCase;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@AllArgsConstructor
public class ProcessAgentFillUseCase {

    private final AgentRepository agentRepository;
    private final ApplicationEventPublisher publisher;
    private final RegisterAgentUseCase registerAgentUseCase;
    private final RecordFillUseCase recordFillUseCase;

    public RecordedFill handle(ReceiveAgentFillForm form) {
        var agent = agentRepository.findById(form.agentId())
                .map(AgentUtils::map)
                .orElse(registerAgentUseCase.handle(new RegisterAgentForm(form.agentId())));
        var agentFill = new AgentFill(
                agent.id(),
                form.tradeId(),
                form.orderId(),
                form.representations(),
                form.accountName(),
                agent.tradingEnvironment(),
                form.price(),
                form.qty()
        );
        publisher.publishEvent(new AgentFillReceived(agentFill));
        return recordFillUseCase.handle(new RecordFillForm(
                agentFill.tradeId(),
                agentFill.orderId(),
                agentFill.accountName(),
                agentFill.tradingEnvironment(),
                agentFill.representations(),
                agentFill.price(),
                agentFill.qty()
        ));
    }

}
