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
public class CreateContractUseCase {

    private final ContractRepository contractRepository;

    public Contract handle(CreateContractForm form) {
        var contract = ContractEntity.create(ContractId.random(), form.representations());
        var saved = contractRepository.save(contract);
        return ContractUtils.map(saved);
    }
}
