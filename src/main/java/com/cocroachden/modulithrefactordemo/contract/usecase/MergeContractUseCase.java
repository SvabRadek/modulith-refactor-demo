package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import com.cocroachden.modulithrefactordemo.contract.ContractId;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractEntity;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class MergeContractUseCase {

    private final ContractRepository contractRepository;

    public Contract handle(MergeContractForm form) {
        var mergedContract = contractRepository.findByRepresentations(
                ContractUtils.map(form.representations())
        ).map(c -> {
            var merged = ContractUtils.map(c).representations().putAll(form.representations());
            return contractRepository.save(c.editRepresentations(merged));
        }).orElseGet(() -> {
            var newContract = ContractEntity.create(ContractId.random(), form.representations());
            return contractRepository.save(newContract);
        });
        return ContractUtils.map(mergedContract);
    }
}
