package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@ApplicationModuleTest
@ActiveProfiles("test")
class MergeContractUseCaseTest {

    @Autowired
    private MergeContractUseCase mergeContractUseCase;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void itCanMergeContracts() {
        var mergeCommand = new MergeContractCommand(new ContractRepresentations(Map.of("format", "value")));
        mergeContractUseCase.handle(mergeCommand);
        var secondMergeCommand = new MergeContractCommand(new ContractRepresentations(Map.of("format", "value", "format2", "value2")));
        var mergedContract = mergeContractUseCase.handle(secondMergeCommand);

        Assertions.assertThat(mergedContract.representations().getRaw())
                .containsKeys("format", "format2");

    }

    @Test
    void itCanMergeOverwriteContracts() {
        var mergeCommand = new MergeContractCommand(new ContractRepresentations(Map.of("A", "A", "B", "B")));
        var secondMergeCommand = new MergeContractCommand(new ContractRepresentations(Map.of("A", "A", "B", "Z")));
        mergeContractUseCase.handle(mergeCommand);
        Assertions.assertThat(contractRepository.findByRepresentation("B", "B")).isPresent();
        mergeContractUseCase.handle(secondMergeCommand);
        Assertions.assertThat(contractRepository.findByRepresentation("B", "B")).isEmpty();
    }

}