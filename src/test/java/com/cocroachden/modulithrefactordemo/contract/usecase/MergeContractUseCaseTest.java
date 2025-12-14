package com.cocroachden.modulithrefactordemo.contract.usecase;

import com.cocroachden.modulithrefactordemo.contract.Contract;
import com.cocroachden.modulithrefactordemo.contract.ContractRepresentations;
import com.cocroachden.modulithrefactordemo.contract.repository.ContractRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
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
        var form1 = new MergeContractForm(new ContractRepresentations(Map.of("format", "value")));
        mergeContractUseCase.handle(form1);
        var form2 = new MergeContractForm(new ContractRepresentations(Map.of("format", "value", "format2", "value2")));
        var mergedContract = mergeContractUseCase.handle(form2);

        Assertions.assertThat(mergedContract).extracting(Contract::representations)
                .extracting(ContractRepresentations::getRepresentations)
                .asInstanceOf(InstanceOfAssertFactories.MAP)
                .hasSize(2);

    }

    @Test
    void itCanMergeOverwriteContracts() {
        var form1 = new MergeContractForm(new ContractRepresentations(Map.of("A", "A", "B", "B")));
        var form2 = new MergeContractForm(new ContractRepresentations(Map.of("A", "A", "B", "Z")));
        mergeContractUseCase.handle(form1);
        Assertions.assertThat(contractRepository.findByRepresentation("B", "B")).isPresent();
        mergeContractUseCase.handle(form2);
        Assertions.assertThat(contractRepository.findByRepresentation("B", "B")).isEmpty();
    }

}