package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;
import com.cocroachden.modulithrefactordemo.contract.domain.ContractId;
import com.cocroachden.modulithrefactordemo.contract.event.ContractCreated;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractEntity;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@AllArgsConstructor
@Slf4j
public class CreateContractUseCase {

    private final ContractRepository contractRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Contract handle(CreateContractForm form) {
        var contractEntity = new ContractEntity(ContractId.random());
        contractEntity.setRepresentations(form.representations());
        var saved = contractRepository.save(contractEntity);
        var dto = ContractUtils.map(saved);
        log.info("Saved contract {}", dto.id());
        publisher.publishEvent(new ContractCreated(dto));
        return dto;
    }
}
