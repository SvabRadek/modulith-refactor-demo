package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.contract.event.ContractDeleted;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@AllArgsConstructor
@Slf4j
public class DeleteContractUseCase {

    private final ContractRepository contractRepository;
    private final ApplicationEventPublisher publisher;

    public void handle(ContractId id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract %s not found.".formatted(id));
        }
        contractRepository.deleteById(id);
        log.info("Deleted contract {}", id);
        publisher.publishEvent(new ContractDeleted(id));
    }
}
