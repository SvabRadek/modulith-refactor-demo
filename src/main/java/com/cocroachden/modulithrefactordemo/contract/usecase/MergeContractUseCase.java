package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.contract.utils.ContractUtils;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class MergeContractUseCase {

    private final ContractRepository contractRepository;
    private final CreateContractUseCase createContractUseCase;
    private final EditContractUseCase editContractUseCase;

    public Contract handle(MergeContractForm form) {
        var foundContract = contractRepository.findByRepresentations(
                ContractUtils.map(form.representations())
        );
        if (foundContract.isPresent()) {
            var merged = ContractUtils.map(foundContract.get()).representations()
                    .putAll(form.representations());
            return editContractUseCase.handleInternally(foundContract.get(), merged);
        }
        return createContractUseCase.handle(new CreateContractForm(form.representations()));
    }
}
