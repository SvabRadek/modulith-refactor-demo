package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.domain.Contract;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import com.cocroachden.modulithrefactordemo.infrastructure.stereotype.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class MergeContractUseCase {

    private final ContractRepository contractRepository;
    private final CreateContractUseCase createContractUseCase;
    private final EditContractUseCase editContractUseCase;

    public Contract handle(MergeContractForm form) {
        var foundContract = contractRepository.findByRepresentations(form.representations());
        if (foundContract.isPresent()) {
            var representations = foundContract.get()
                    .getRepresentations()
                    .putAll(form.representations().toList());
            return editContractUseCase.handleInternally(foundContract.get(), representations);
        }
        return createContractUseCase.handle(new CreateContractForm(form.representations()));
    }
}
