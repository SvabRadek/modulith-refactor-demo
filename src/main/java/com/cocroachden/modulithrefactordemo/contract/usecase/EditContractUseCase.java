package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractEntity;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@AllArgsConstructor
@Slf4j
public class EditContractUseCase {

    private final ContractRepository contractRepository;

    public Contract handle(EditContractForm form) throws ContractNotFound {
        return contractRepository.findById(form.id())
                .map(entity -> entity.editRepresentations(form.representations()))
                .map(contractRepository::save)
                .map(ContractUtils::map)
                .orElseThrow(() -> new ContractNotFound(form.id()));
    }

    Contract handleInternally(ContractEntity entity, ContractRepresentations representations) {
        entity.editRepresentations(representations);
        var saved = contractRepository.save(entity);
        return ContractUtils.map(saved);
    }
}
