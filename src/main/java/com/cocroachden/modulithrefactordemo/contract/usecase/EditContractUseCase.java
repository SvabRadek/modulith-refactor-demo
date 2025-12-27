package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.event.ContractEdited;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractEntity;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
@AllArgsConstructor
@Slf4j
public class EditContractUseCase {

    private final ContractRepository contractRepository;
    private final ApplicationEventPublisher publisher;

    public Contract handle(EditContractForm form) throws ContractNotFound {
        var contract = contractRepository.findById(form.id());
        if (contract.isEmpty()) {
            throw new ContractNotFound(form.id());
        }
        contract.get().setRepresentations(form.representations());
        var saved = contractRepository.save(contract.get());
        var dto = ContractUtils.map(saved);
        log.info("Edited contract {}", dto.id());
        publisher.publishEvent(new ContractEdited(dto));
        return dto;
    }

    Contract handleInternally(ContractEntity entity, ContractRepresentations representations) {
        entity.setRepresentations(representations);
        var saved = contractRepository.save(entity);
        var dto = ContractUtils.map(saved);
        log.info("Edited contract {}", dto.id());
        publisher.publishEvent(new ContractEdited(dto));
        return dto;
    }
}
